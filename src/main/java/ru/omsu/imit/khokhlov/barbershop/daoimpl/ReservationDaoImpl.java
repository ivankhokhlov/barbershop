package ru.omsu.imit.khokhlov.barbershop.daoimpl;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.ReservationDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Component
public class ReservationDaoImpl extends BaseDaoImpl implements ReservationDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDaoImpl.class);
    @Override
    public Reservation insert(Reservation reservation) {
        LOGGER.debug("DAO insert reservation {}", reservation);
        try (SqlSession sqlSession = getSession()) {
            try {
                getReservationMapper(sqlSession).insert(reservation);
                for(Service service:reservation.getServices()){
                    getServiceReservationMapper(sqlSession).insert(service,reservation);
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert reservation {} {}", reservation, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return reservation;
    }

    @Override
    public Reservation updatePatient(Reservation reservation) {
        LOGGER.debug("DAO update reservation {}", reservation);
        try (SqlSession sqlSession = getSession()) {
            try {
                getReservationMapper(sqlSession).updatePatient(reservation);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update reservation {} {}", reservation, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return reservation;
    }

    @Override
    public Reservation getById(int id) {
        LOGGER.debug("DAO get reservation by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getReservationMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get reservation {}",id, ex);
            throw ex;
        }
    }

    @Override
    public Reservation getByTicket(String ticket) {
        LOGGER.debug("DAO get reservation by ticket {}", ticket);
        try (SqlSession sqlSession = getSession()) {
            return getReservationMapper(sqlSession).getByTicket(ticket);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get reservation {}",ticket, ex);
            throw ex;
        }
    }

    @Override
    public Reservation getByDateTimeAndMaster(LocalDate date, LocalTime time, Master master) {
        LOGGER.debug("DAO get reservation by Date,time,master {},{},{}", date,time,master);
        try (SqlSession sqlSession = getSession()) {
            return getReservationMapper(sqlSession).getByDateTimeAndMaster(date,time,master);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get reservation by Date,time,master {},{},{}", date, time, master);
            throw ex;
        }
    }

    @Override
    public List<Reservation> getByDateTimeStartTimeEndAndMaster(LocalDate date, LocalTime timeStart,
                                                                  LocalTime timeEnd, Master master) {
        LOGGER.debug("DAO get reservation by date,timeStart,timeEnd,master {},{},{},{}", date,timeStart,timeEnd,master);
        try (SqlSession sqlSession = getSession()) {
            return getReservationMapper(sqlSession).getByDateTimeStartTimeEndAndDoctorId(date,timeStart,timeEnd,master);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get reservation by date,timeStart,timeEnd,master {},{},{},{}",
                    date,timeStart,timeEnd,master,ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All reservation");
        try (SqlSession sqlSession = getSession()) {
            try {
                getReservationMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all reservation", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
