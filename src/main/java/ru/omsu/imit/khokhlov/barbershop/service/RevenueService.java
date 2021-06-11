package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.omsu.imit.khokhlov.barbershop.dto.response.RevenueResponse;
import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RevenueService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceService.class);

    public RevenueResponse getMasterRevenueByDate(Integer masterid,
                                                  String startDate,
                                                  String endDate, String uuid) {
        LOGGER.debug("Service getMasterRevenueByDate revenue by masterid,startDate,endDate,uuid {},{},{},{}",
                masterid, startDate, endDate, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);

            Master userMaster = masterDao.getById(masterid);
            if (userMaster == null || userMaster.getUser().getUserType() != UserType.MASTER) {
                throw new ServerException(ErrorCodes.MASTER_ID_NOT_FOUND);
            }
            User user = cookie.getUser();
            if (!(user.getUserType() == UserType.ADMIN || user.getId() != masterid)) {
                throw new ServerException(ErrorCodes.ACCESS_DENIED);
            }
            LocalDate dateStart = LocalDate.now().minusMonths(1);
            LocalDate dateEnd = LocalDate.now();
            if (startDate != null && !"".equals(startDate)) {
                dateStart = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                dateEnd = dateStart.plusMonths(1);
                if (endDate != null && !"".equals(endDate)) {
                    dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                }
            }
            Integer revenue = serviceDao.getRevenueByMasterAndDate(userMaster, dateStart, dateEnd);
            return revenue == null ? new RevenueResponse(0) : new RevenueResponse(revenue);
        } catch (Exception ex) {
            LOGGER.info("Service can't getMasterRevenueByDate ", ex);
            throw ex;
        }

    }

    public RevenueResponse getRevenueByDate(String startDate,
                                            String endDate, String uuid) {
        LOGGER.debug("Service getRevenueByDate revenue by startDate,endDate,uuid {},{},{}",
                startDate, endDate, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            User user = cookie.getUser();
            if (!(user.getUserType() == UserType.ADMIN)) {
                throw new ServerException(ErrorCodes.ACCESS_DENIED);
            }
            LocalDate dateStart = LocalDate.now().minusMonths(1);
            LocalDate dateEnd = LocalDate.now();
            if (startDate != null && !"".equals(startDate)) {
                dateStart = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                dateEnd = dateStart.plusMonths(1);
                if (endDate != null && !"".equals(endDate)) {
                    dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                }
            }
            Integer revenue = serviceDao.getRevenueByDate(dateStart, dateEnd);
            return revenue == null ? new RevenueResponse(0) : new RevenueResponse(revenue);
        } catch (Exception ex) {
            LOGGER.info("Service can't getRevenueByDate ", ex);
            throw ex;
        }

    }
}
