package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.khokhlov.barbershop.dto.request.RecordToTheMasterRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.ServiceNameRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.RecordToTheMasterResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ReservationResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ServiceResponse;
import ru.omsu.imit.khokhlov.barbershop.model.user.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ReservationService extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    public RecordToTheMasterResponse recordToTheMaster(RecordToTheMasterRequest recordToTheMasterRequest, String uuid) {
        LOGGER.debug("Service recordToTheMaster recordToTheMasterRequest,uuid {},{}", recordToTheMasterRequest, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            if (cookie.getUser().getUserType() != UserType.CLIENT) {
                throw new ServerException(ErrorCodes.ACCESS_DENIED);
            }
            Integer masterId = recordToTheMasterRequest.getMasterId();
            String specialization = recordToTheMasterRequest.getSpecialization();
            System.out.println(masterId + "" + specialization);
            responseProcessor.checkRecordRequest(masterId, specialization);
            Client client = clientDao.getById(cookie.getUser().getId());
            List<ServiceNameRequest> serviceNameRequests = recordToTheMasterRequest.getServiceNameRequests();
            List<Service> services = new ArrayList<>();
            for (ServiceNameRequest serviceNameRequest : serviceNameRequests) {
                Service service = serviceDao.getByName(serviceNameRequest.getName());

                if (service == null) {
                    throw new ServerException(ErrorCodes.SERVICE_NOT_FOUND);
                }
                services.add(service);
            }
            Set<Integer> servicesClient = services.stream().map(Service::getId).collect(Collectors.toSet());

            int duration = services.stream().mapToInt(Service::getDuration).sum();
            int cost = services.stream().mapToInt(Service::getPrice).sum();
            LocalDate date = LocalDate.parse(recordToTheMasterRequest.getDate());
            LocalTime timeStart = LocalTime.parse(recordToTheMasterRequest.getTimeStart());
            LocalTime timeEnd = timeStart.plusMinutes(duration);

            if (masterId != null) {
                Master master = masterDao.getById(masterId);
                if (master == null) {
                    throw new ServerException(ErrorCodes.MASTER_ID_NOT_FOUND);
                }
                Set<Integer> servicesMaster = master.getService().stream().map(Service::getId).collect(Collectors.toSet());
                servicesClient.removeAll(servicesMaster);
                if (servicesClient.size() != 0) {
                    throw new ServerException(ErrorCodes.SERVICE_NOT_PROVIDED);
                }

                List<Reservation> reservations = reservationDao.getByDateTimeStartTimeEndAndMaster(date, timeStart, timeEnd, master);
                if (!((reservations == null) || reservations.isEmpty())) {
                    throw new ServerException(ErrorCodes.RESERVATION_IS_OCCUPIED);
                }
                DaySchedule daySchedule = dayScheduleDao.getByMasterAndDate(master, date);
                String receipt = responseProcessor.getReceipt(date, timeStart, timeEnd, master, client, cost);
                Reservation reservation = new Reservation(daySchedule, timeStart, timeEnd, receipt, client, services);
                reservationDao.insert(reservation);
                User userFromMaster = master.getUser();
                List<ServiceResponse> serviceResponses = responseProcessor.getResponse(services);
                return new RecordToTheMasterResponse(masterId, userFromMaster.getFirstName(), userFromMaster.getLastName(),
                        userFromMaster.getPatronymic(), master.getSpecialization().getName(),
                        serviceResponses,
                        date, timeStart, timeEnd,
                        cost, receipt);
            }
            Specialization specializationObj = specializationDao.getByName(specialization);
            if (specializationObj == null) {
                throw new ServerException(ErrorCodes.SPECIALIZATION_NOT_FOUND);
            }
            List<Master> masters = masterDao.getBySpecialization(specializationObj);
            if (masters == null) {
                throw new ServerException(ErrorCodes.MASTER_SPECIALIZATION_NOT_FOUND);
            }
            Collections.shuffle(masters);
            for (Master master : masters) {
                List<Reservation> reservations = reservationDao.getByDateTimeStartTimeEndAndMaster(date, timeStart, timeEnd, master);
                if ((reservations == null) || reservations.isEmpty()) {
                    Set<Integer> servicesMaster = master.getService().stream().map(Service::getId).collect(Collectors.toSet());
                    servicesClient.removeAll(servicesMaster);
                    if (servicesClient.size() != 0) {
                        continue;
                    }
                    DaySchedule daySchedule = dayScheduleDao.getByMasterAndDate(master, date);
                    String receipt = responseProcessor.getReceipt(date, timeStart, timeEnd, master, client, cost);

                    Reservation reservation = new Reservation(daySchedule, timeStart, timeEnd, receipt, client, services);
                    reservationDao.insert(reservation);
                    User userFromMaster = master.getUser();
                    List<ServiceResponse> serviceResponses = responseProcessor.getResponse(services);
                    return new RecordToTheMasterResponse(masterId, userFromMaster.getFirstName(), userFromMaster.getLastName(),
                            userFromMaster.getPatronymic(), master.getSpecialization().getName(),
                            serviceResponses,
                            date, timeStart, timeEnd,
                            cost, receipt);

                }

            }
            throw new ServerException(ErrorCodes.RESERVATION_IS_NOT_POSSIBLE);
        } catch (Exception ex) {
            LOGGER.info("Service can't recordToTheDoctor ", ex);
            throw ex;
        }
    }

    public ReservationResponse getInfoByReceipt(String receipt, String uuid) {
        LOGGER.debug("Service getInfoByReceipt receipt,uuid {},{}", receipt, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);

            Reservation reservation = reservationDao.getByReceipt(receipt);
            if (reservation == null) {
                throw new ServerException(ErrorCodes.RESERVATION_NOT_FOUND);
            }

            if (cookie.getUser().getId() == reservation.getClient().getUser().getId()) {

                return responseProcessor.getResponse(reservation);

            }
            Master master = masterDao.getByReservation(reservation);
            if (cookie.getUser().getId() == (master.getUser().getId())) {

                return responseProcessor.getResponse(reservation);

            }
            responseProcessor.checkAdminPermission(cookie.getUser());
            return responseProcessor.getResponse(reservation);

        } catch (Exception ex) {
            LOGGER.info("Service can't getInfoByReceipt ", ex);
            throw ex;
        }
    }

    public void deleteRecordToTheMaster(String receipt, String uuid) {
        LOGGER.debug("Service deleteRecordToTheMaster receipt,uuid {},{}", receipt, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);

            Reservation reservation = reservationDao.getByReceipt(receipt);
            if (reservation == null) {
                throw new ServerException(ErrorCodes.RESERVATION_NOT_FOUND);
            }
            if (cookie.getUser().getId() == reservation.getClient().getUser().getId()) {
                reservationDao.deleteByReceipt(receipt);
                return;
            }
            Master master = masterDao.getByReservation(reservation);
            if (cookie.getUser().getId() == (master.getUser().getId())) {
                reservationDao.deleteByReceipt(receipt);
                return;

            }
            responseProcessor.checkAdminPermission(cookie.getUser());
            reservationDao.deleteByReceipt(receipt);

        } catch (Exception ex) {
            LOGGER.info("Service can't deleteRecordToTheDoctor ", ex);
            throw ex;
        }
    }
}
