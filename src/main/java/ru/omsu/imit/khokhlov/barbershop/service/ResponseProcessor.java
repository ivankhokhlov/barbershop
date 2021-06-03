package ru.omsu.imit.khokhlov.barbershop.service;

import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.*;
import ru.omsu.imit.khokhlov.barbershop.daoimpl.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.BoxForDayScheduleRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.WeekScheduleRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ResponseProcessor {
    private static UserDao userDao = new UserDaoImpl();
    private static AdminDao adminDao = new AdminDaoImpl();
    private static ClientDao clientDao = new ClientDaoImpl();
    private static MasterDao masterDao = new MasterDaoImpl();
    private static CookieDao cookieDao = new CookieDaoImpl();

    public Object getResponse(User user) {
        UserType userType = user.getUserType();
        switch (userType) {
            case ADMIN:
                Admin admin = adminDao.getById(user.getId());
                return getResponse(admin);

            case MASTER:
                Master master = masterDao.getById(user.getId());
                return getResponse(master,master.getUser());

            case CLIENT:
                Client client = clientDao.getById(user.getId());
                return getResponse(client);
        }
        return null;
    }

    public AdminInfoResponse getResponse(Admin admin) {
        User adminUser = admin.getUser();
        return new AdminInfoResponse(adminUser.getId(),
                adminUser.getFirstName(),
                adminUser.getLastName(), adminUser.getPatronymic(), admin.getPosition());

    }

    public MasterInfoWithoutScheduleResponse getResponse(Master master,User userRequest) {
        User masterUser = master.getUser();
        List<Service> services = master.getService();
        List<ServiceResponse> serviceResponses =getResponse(services);
        List<DaySchedule> daySchedules = master.getDaySchedulesList();
        if (daySchedules != null) {
            List<DayScheduleResponse> schedule = new ArrayList<>();
            for (DaySchedule daySchedule : daySchedules) {
                LocalDate curDate = daySchedule.getCurDate();
                List<ReservationWithoutClientResponse> receptionResponses = new ArrayList<>();
                for (Reservation reservation : daySchedule.getReservations()) {

                    LocalTime timeStart = reservation.getTimeStart();
                    LocalTime timeEnd = reservation.getTimeEnd();
                    Client clientInReservation = reservation.getClient();

                    if (clientInReservation != null) {
                        if (userRequest.equals(clientInReservation.getUser()) || userRequest.getUserType() != UserType.CLIENT) {
                            ClientInfoResponse clientInfoResponse = getResponse(clientInReservation);
                            ReservationResponse reservationResponse = new ReservationResponse(timeStart,
                                    timeEnd, clientInfoResponse);
                            receptionResponses.add(reservationResponse);
                        } else {
                            receptionResponses.add(new ReservationWithoutClientResponse(timeStart,
                                    timeEnd));
                        }
                    } else {
                        receptionResponses.add(new ReservationWithoutClientResponse(timeStart,timeEnd));
                    }

                }
                DayScheduleResponse dayScheduleResponse = new DayScheduleResponse(curDate, receptionResponses);
                schedule.add(dayScheduleResponse);
            }


            return new MasterInfoResponse(masterUser.getId(),
                    masterUser.getFirstName(),
                    masterUser.getLastName(),
                    masterUser.getPatronymic(),
                    new SpecializationResponse(master.getSpecialization().getId(),
                            master.getSpecialization().getName()),serviceResponses,schedule);
        }
        return new MasterInfoWithoutScheduleResponse(
                masterUser.getId(),
                masterUser.getFirstName(),
                masterUser.getLastName(),
                masterUser.getPatronymic(),
                new SpecializationResponse(
                        master.getSpecialization().getId(),
                        master.getSpecialization().getName()),
                serviceResponses);
    }
    public List<ServiceResponse> getResponse(List<Service> services){
        List<ServiceResponse> serviceResponses = new ArrayList<>(services.size());
        for (Service service : services) {
            serviceResponses.add(new ServiceResponse(service.getId(),
                    service.getName(), service.getPrice(),service.getDuration()));
        }
        return serviceResponses;
    }


    public ClientInfoResponse getResponse(Client client) {
        User clientUser = client.getUser();
        return new ClientInfoResponse(clientUser.getId(),
                clientUser.getFirstName(),
                clientUser.getLastName(), clientUser.getPatronymic(),
                client.getEmail(), client.getAddress(), client.getPhone());

    }

    public Cookie getCookie(String uuid) {
        Cookie cookie = cookieDao.getByUUID(uuid);
        if (cookie == null) {
            throw new ServerException(ErrorCodes.COOKIE_NOT_FOUND);
        }

        return cookie;
    }
    public String getReceipt(LocalDate date,LocalTime timeStart,LocalTime timeEnd,Master master,Client client,int cost){
        String formattedDate = date.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String formattedTimeStart = timeStart.format(DateTimeFormatter.ofPattern("HHmm"));
        String formattedTimeEnd= timeEnd.format(DateTimeFormatter.ofPattern("HHmm"));
        return  "Master<" + master.getUser().getId() + ">"
                +"Client<"+client.getUser().getId()+">" +
                formattedDate +'('+ formattedTimeStart +"\\|"+formattedTimeEnd+')'+cost;
    }


    public void checkAdminPermission(User user) {
        if (user.getUserType() != UserType.ADMIN) {
            throw new ServerException(ErrorCodes.ACCESS_DENIED);
        }
    }

    public void checkAdminOrMasterPermission(User user) {
        if (!(user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.MASTER)) {
            throw new ServerException(ErrorCodes.ACCESS_DENIED);
        }
    }

    public void checkMasterPermission(User user) {
        if (user.getUserType() != UserType.MASTER) {
            throw new ServerException(ErrorCodes.ACCESS_DENIED);
        }
    }

    public void checkLogin(String login) {
        if (userDao.getByLogin(login) != null) {
            throw new ServerException(ErrorCodes.USER_ALREADY_REGISTER);
        }
    }

    public void checkSchedule(List<BoxForDayScheduleRequest> weekDaysSchedule, WeekScheduleRequest weekSchedule) {
        if ((weekDaysSchedule == null) && (weekSchedule == null)) {
            throw new ServerException(ErrorCodes.ALL_SCHEDULE_IS_EMPTY);
        }
        if ((weekDaysSchedule != null) && (weekSchedule != null)) {
            throw new ServerException(ErrorCodes.INVALID_SCHEDULE);
        }
    }

    public void checkRecordRequest(Integer masterid, String speciality) {
        if ((masterid == null) && (speciality == null)) {
            throw new ServerException(ErrorCodes.RECORD_TO_THE_MASTER_IS_EMPTY);
        }
        if ((masterid != null) && (speciality != null)) {
            throw new ServerException(ErrorCodes.INVALID_RECORD_TO_THE_MASTER);
        }
    }

}
