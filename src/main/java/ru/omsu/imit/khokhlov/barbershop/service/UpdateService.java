package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omsu.imit.khokhlov.barbershop.dto.request.*;
import ru.omsu.imit.khokhlov.barbershop.dto.response.AdminInfoResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ClientInfoResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.MasterInfoWithoutScheduleResponse;
import ru.omsu.imit.khokhlov.barbershop.model.user.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UpdateService extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateService.class);
    private ScheduleService scheduleService;
    @Autowired
    public UpdateService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    public AdminInfoResponse updateAdmin(UpdateAdminRequest updateAdminRequest, String uuid) {
        LOGGER.debug("Service updateAdmin UpdateAdminRequest,uuid {},{}", updateAdminRequest, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            if (!cookie.getUser().getPassword().equals(updateAdminRequest.getOldPassword())) {
                throw new ServerException(ErrorCodes.INVALID_PASSWORD);
            }

            Admin newAdmin = new Admin(new User(cookie.getUser().getId(), updateAdminRequest.getFirstName(),
                    updateAdminRequest.getLastName(),
                    updateAdminRequest.getPatronymic(),
                    null,
                    updateAdminRequest.getNewPassword(), null), updateAdminRequest.getPosition());
            Admin admin = adminDao.update(newAdmin);
            return responseProcessor.getResponse(admin);
        } catch (Exception ex) {
            LOGGER.info("Service can't updateAdmin {},{}",updateAdminRequest, uuid, ex);
            throw ex;
        }
    }


    public MasterInfoWithoutScheduleResponse updateSchedule(int masterId, UpdateScheduleRequest updateScheduleRequest, String uuid) {
        LOGGER.debug("Service updateSchedule masterId,updateScheduleRequest,uuid {},{},{}", masterId, updateScheduleRequest, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            responseProcessor.checkAdminPermission(cookie.getUser());
            Master master = masterDao.getById(masterId);
            if (master == null) {
                throw new ServerException(ErrorCodes.MASTER_ID_NOT_FOUND);
            }
            LocalDate dateStart = updateScheduleRequest.getDateStart();
            LocalDate dateEnd = updateScheduleRequest.getDateEnd();

            List<DaySchedule> list = master.getDaySchedulesList().stream().filter((a) -> (
                    a.getCurDate().isBefore(dateEnd)
                            || a.getCurDate().equals(dateEnd))
                    && (a.getCurDate().isAfter(dateStart)
                    || a.getCurDate().equals(dateStart))).collect(Collectors.toList());
            if (!list.isEmpty()) {
                list.parallelStream().map(DaySchedule::getReservations).forEach(reservations -> {
                    for (Reservation reservation : reservations) {
                        if (reservation.getClient() != null) {
                            throw new ServerException(ErrorCodes.SCHEDULE_IS_OCCUPIED);
                        }
                    }
                });
            }
            deleteDaySchedule(list);
            List<BoxForDayScheduleRequest> weekDaysSchedule = updateScheduleRequest.getWeekDaysSchedule();
            WeekScheduleRequest weekSchedule = updateScheduleRequest.getWeekSchedule();
            responseProcessor.checkSchedule(weekDaysSchedule, weekSchedule);
            return scheduleService.insertSchedule(weekDaysSchedule, weekSchedule, dateStart, dateEnd, master );
        } catch (Exception ex) {
            LOGGER.info("Service can't updateSchedule {},{},{}", masterId, updateScheduleRequest, uuid, ex);
            throw ex;
        }
    }

    public ClientInfoResponse updateClient(UpdateClientRequest updateClientRequest, String uuid) {
        Cookie cookie = responseProcessor.getCookie(uuid);
        if (!cookie.getUser().getPassword().equals(updateClientRequest.getOldPassword())) {
            throw new ServerException(ErrorCodes.INVALID_PASSWORD);
        }

        Client newClient = new Client(new User(cookie.getUser().getId(), updateClientRequest.getFirstName(),
                updateClientRequest.getLastName(),
                updateClientRequest.getPatronymic(),
                null,
                updateClientRequest.getNewPassword(), null),
                updateClientRequest.getEmail(),
                updateClientRequest.getAddress(), updateClientRequest.getPhone());
        Client client = clientDao.update(newClient);
        return responseProcessor.getResponse(client);
    }
    private void deleteDaySchedule(List<DaySchedule> daySchedules) {
        for (DaySchedule daySchedule : daySchedules) {
            dayScheduleDao.delete(daySchedule);
        }
    }
}
