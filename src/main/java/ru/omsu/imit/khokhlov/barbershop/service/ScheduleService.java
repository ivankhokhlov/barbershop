package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.omsu.imit.khokhlov.barbershop.dto.request.BoxForDayScheduleRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.DayScheduleRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.WeekScheduleRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.MasterInfoWithoutScheduleResponse;
import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ScheduleService extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);


    public MasterInfoWithoutScheduleResponse insertSchedule(List<BoxForDayScheduleRequest> weekDaysSchedule,
                                                            WeekScheduleRequest weekSchedule,
                                                            LocalDate dateStart, LocalDate dateEnd, Master newMaster) {
        if ((weekSchedule != null)) {

            LocalTime timeStart = weekSchedule.getTimeStart();
            LocalTime timeEnd = weekSchedule.getTimeEnd();
            LocalDate curDate = dateStart;

            Set<Integer> weekDays = weekSchedule.getWeekDay();
            if (weekDays == null || weekDays.isEmpty()) {
                List<DayOfWeek> dayOfWeeks = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
                while (curDate.isBefore(dateEnd) || curDate.equals(dateEnd)) {
                    if (!dayOfWeeks.contains(curDate.getDayOfWeek())) {
                        DaySchedule daySchedule = new DaySchedule(newMaster, curDate, timeStart, timeEnd);
                        dayScheduleDao.insert(daySchedule);
                    }
                    curDate = curDate.plusDays(1);
                }
            } else {
                List<DayOfWeek> dayOfWeeks = new ArrayList<>();
                for (Integer day : weekDays) {
                    DayOfWeek dayOfWeek = DayOfWeek.of(day);
                    dayOfWeeks.add(dayOfWeek);
                }
                while (curDate.isBefore(dateEnd) || curDate.equals(dateEnd)) {
                    if (dayOfWeeks.contains(curDate.getDayOfWeek())) {
                        DaySchedule daySchedule = new DaySchedule(newMaster, curDate, timeStart, timeEnd);
                        dayScheduleDao.insert(daySchedule);
                    }
                    curDate = curDate.plusDays(1);
                }
            }
            Master master = masterDao.getById(newMaster.getUser().getId());
            return responseProcessor.getResponse(master, master.getUser());

        }

        Map<DayOfWeek, DayScheduleRequest> dayScheduleRequestMap = new HashMap<>();
        for (BoxForDayScheduleRequest daySchedule : weekDaysSchedule) {
            DayOfWeek dayOfWeek = daySchedule.getDaySchedule().getWeekDay();
            if (dayScheduleRequestMap.containsKey(dayOfWeek)) {

                throw new ServerException(ErrorCodes.INVALID_WEEKDAY);
            }
            dayScheduleRequestMap.put(dayOfWeek, daySchedule.getDaySchedule());

        }
        LocalDate curDate = dateStart;
        while (curDate.isBefore(dateEnd) || curDate.equals(dateEnd)) {
            if (dayScheduleRequestMap.containsKey(curDate.getDayOfWeek())) {
                DayScheduleRequest dayScheduleRequest = dayScheduleRequestMap.get(curDate.getDayOfWeek());
                DaySchedule daySchedule = new DaySchedule(newMaster, curDate, dayScheduleRequest.getTimeStart(), dayScheduleRequest.getTimeEnd());
                dayScheduleDao.insert(daySchedule);
            }
            curDate = curDate.plusDays(1);
        }
        Master master = masterDao.getById(newMaster.getUser().getId());
        return responseProcessor.getResponse(master, master.getUser());
    }

    public MasterInfoWithoutScheduleResponse getMasterSchedule(Integer masterid, String schedule, String startDate,
                                                               String endDate, String uuid) {
        LOGGER.debug("Service getMasterSchedule masterid,schedule,startDate,endDate,uuid {},{},{},{},{}", masterid, schedule, startDate, endDate, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);

            User userMaster = userDao.getById(masterid);
            if (userMaster == null || userMaster.getUserType() != UserType.MASTER) {
                throw new ServerException(ErrorCodes.MASTER_ID_NOT_FOUND);
            }

            Master master = masterDao.getById(masterid);
            master.setDaySchedulesList(null);
            if (schedule != null && schedule.contains("yes")) {
                LocalDate dateStart = LocalDate.now();
                LocalDate dateEnd = dateStart.plusMonths(1);
                if (startDate != null && !"".equals(startDate)) {
                    dateStart = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    dateEnd = dateStart.plusMonths(1);
                    if (endDate != null && !"".equals(endDate)) {
                        dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    }
                }
                master.setDaySchedulesList(dayScheduleDao.getByMasterAndDate(master, dateStart, dateEnd));
            }
            return responseProcessor.getResponse(master, cookie.getUser());
        } catch (Exception ex) {
            LOGGER.info("Service can't getMasterSchedule ", ex);
            throw ex;
        }

    }

    public List<MasterInfoWithoutScheduleResponse> getMastersSchedule(String schedule, String specialization, String startDate,
                                                                      String endDate, String uuid) {
        LOGGER.debug("Service getMasterSchedules schedule,specialization,startDate,endDate,uuid {},{},{},{},{}", schedule, specialization, startDate, endDate, uuid);
        try {
            List<MasterInfoWithoutScheduleResponse> responses = new ArrayList<>();
            System.out.println(specialization);
            if (specialization != null) {
                Specialization specializationObj = specializationDao.getByName(specialization);
                System.out.println(specialization);
                if (specializationObj != null) {
                    List<Master> masters = masterDao.getBySpecialization(specializationObj);
                    System.out.println(masters);
                    for (Master master : masters) {
                        responses.add(getMasterSchedule(master.getUser().getId(), schedule, startDate, endDate, uuid));
                    }
                    return responses;
                }
            }
            List<Master> masters = masterDao.getAllMaster();
            for (Master master : masters) {
                responses.add(getMasterSchedule(master.getUser().getId(), schedule, startDate, endDate, uuid));
            }
            return responses;
        } catch (Exception ex) {
            LOGGER.info("Service can't getMasterSchedules ", ex);
            throw ex;
        }

    }

}
