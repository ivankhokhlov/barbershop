package ru.omsu.imit.khokhlov.barbershop.daoimpl;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.DayScheduleDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;

import java.time.LocalDate;
import java.util.List;


@Component
public class DayScheduleDaoImpl extends BaseDaoImpl implements DayScheduleDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DayScheduleDaoImpl.class);

    @Override
    public DaySchedule insert(DaySchedule daySchedule) {
        LOGGER.debug("DAO insert daySchedule {}", daySchedule);
        try (SqlSession sqlSession = getSession()) {
            try {
                getDayScheduleMapper(sqlSession).insert(daySchedule);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert daySchedule {} {}", daySchedule, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return daySchedule;
    }

    @Override
    public DaySchedule getById(int id) {
        LOGGER.debug("DAO get daySchedule by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getDayScheduleMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get daySchedule {}",id,ex);
            throw ex;
        }
    }

    @Override
    public List<DaySchedule> getByMasterAndDate(Master master, LocalDate dateStart, LocalDate dateEnd) {
        LOGGER.debug("DAO get daySchedule by master,dateStart,dateEnd {},{},{}", master, dateStart, dateEnd);
        try (SqlSession sqlSession = getSession()) {
            return getDayScheduleMapper(sqlSession).getByMasterAndDateStartDateEnd(master, dateStart, dateEnd);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get daySchedule by master dateStart dateEnd {},{},{}", master, dateStart, dateEnd,ex);
            throw ex;
        }
    }
    @Override
    public DaySchedule getByMasterAndDate(Master master, LocalDate date) {
        LOGGER.debug("DAO get daySchedule by master,date {},{}", master, date);
        try (SqlSession sqlSession = getSession()) {
            return getDayScheduleMapper(sqlSession).getByMasterAndDate(master, date);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get daySchedule by master,date {},{}", master, date,ex);
            throw ex;
        }
    }

    @Override
    public List<DaySchedule> getByDate(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd) {
        LOGGER.debug("DAO get daySchedule by dateStart,dateEnd {},{}", dateStart, dateEnd);
        try (SqlSession sqlSession = getSession()) {
            return getDayScheduleMapper(sqlSession).getByDate(dateStart, dateEnd);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get daySchedule by dateStart,dateEnd {},{}", dateStart, dateEnd,ex);
            throw ex;
        }
    }

    @Override
    public void delete(DaySchedule daySchedule) {
        LOGGER.debug("DAO Delete daySchedule");
        try (SqlSession sqlSession = getSession()) {
            try {
                getDayScheduleMapper(sqlSession).delete(daySchedule);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete daySchedule", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All daySchedule");
        try (SqlSession sqlSession = getSession()) {
            try {
                getDayScheduleMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all daySchedule", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
