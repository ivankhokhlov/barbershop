package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import ru.omsu.imit.khokhlov.barbershop.dto.request.*;
import ru.omsu.imit.khokhlov.barbershop.dto.response.AdminInfoResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ClientInfoResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.MasterInfoWithoutScheduleResponse;
import ru.omsu.imit.khokhlov.barbershop.model.user.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.*;

@org.springframework.stereotype.Service
public class RegisterService extends BaseService {
    private ScheduleService scheduleService;
    @Autowired
    public RegisterService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterService.class);
    public AdminInfoResponse registerAdmin(RegisterAdminRequest request, String uuid) {
        LOGGER.debug("RegisterService registerAdmin RegisterAdminRequest,uuid {},{}", request, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            responseProcessor.checkAdminPermission(cookie.getUser());
            responseProcessor.checkLogin(request.getLogin());
            Admin newAdmin = new Admin(new User(request.getFirstName(), request.getFirstName(), request.getPatronymic(),
                    request.getLogin(), request.getPassword(), UserType.ADMIN), request.getPosition());
            adminDao.insert(newAdmin);
            return responseProcessor.getResponse(newAdmin);
        } catch (Exception ex) {
            LOGGER.info("RegisterService can't registerAdmin {}{}", request, uuid, ex);
            throw ex;
        }
    }

    public MasterInfoWithoutScheduleResponse registerMaster(RegisterMasterRequest request, String uuid) {
        LOGGER.debug("RegisterService registerMaster RegisterMasterRequest,uuid {},{}", request, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            System.out.println(cookie);
            responseProcessor.checkAdminPermission(cookie.getUser());
            responseProcessor.checkLogin(request.getLogin());
            LocalDate dateStart = LocalDate.parse(request.getDateStart());
            LocalDate dateEnd = LocalDate.parse( request.getDateEnd());

            String specialization = request.getSpecialization();
            Specialization specializationObj = specializationDao.getByName(specialization);
            if (specializationObj == null) {
                specializationObj = specializationDao.insert(new Specialization(specialization));
            }

            List<BoxForDayScheduleRequest> weekDaysSchedule = request.getWeekDaysSchedule();
            WeekScheduleRequest weekSchedule = request.getWeekSchedule();
            responseProcessor.checkSchedule(weekDaysSchedule, weekSchedule);
            List<ServiceRequest> serviceRequests=request.getServiceRequests();
            List<Service> services= new ArrayList<>();
            for (ServiceRequest serviceRequest:serviceRequests){

                Service service =serviceDao.getByName(serviceRequest.getName());
                if (service==null){
                    service= new Service(serviceRequest.getName(),serviceRequest.getPrice(),serviceRequest.getDuration());
                service=serviceDao.insert(service);
                }
                services.add(service);

            }

            Master newMaster = new Master(new User(request.getFirstName(), request.getFirstName(),
                    request.getPatronymic(),
                    request.getLogin(), request.getPassword(), UserType.MASTER), specializationObj,
                    services, null);
            masterDao.insert(newMaster);
            try {
                return scheduleService.insertSchedule(weekDaysSchedule, weekSchedule, dateStart, dateEnd,newMaster);
            } catch (Exception ex) {
                userDao.deleteById(newMaster.getUser().getId());
                throw ex;
            }
        } catch (Exception ex) {
            LOGGER.info("RegisterService can't registerDoctor ", ex);
            throw ex;
        }

    }
    public ClientInfoResponse registerClient(RegisterClientRequest request) {
        LOGGER.debug("RegisterService registerClient RegisterClientRequest {}", request);
        try {
            responseProcessor.checkLogin(request.getLogin());
            Client newClient = new Client(new User(request.getFirstName(), request.getLastName(), request.getPatronymic(),
                    request.getLogin(), request.getPassword(), UserType.CLIENT),
                    request.getEmail(),
                    request.getAddress(),
                    request.getPhone().replace("-", ""));
            clientDao.insert(newClient);
            return responseProcessor.getResponse(newClient);
        } catch (Exception ex) {
            LOGGER.info("RegisterService can't registerClient {}", request, ex);
            throw ex;
        }
    }


}
