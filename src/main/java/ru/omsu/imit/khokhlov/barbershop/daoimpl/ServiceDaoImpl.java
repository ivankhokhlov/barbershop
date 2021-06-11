package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.ServiceDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

import java.time.LocalDate;
import java.util.List;

@Component
public class ServiceDaoImpl extends BaseDaoImpl implements ServiceDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDaoImpl.class);

    @Override
    public Service insert(Service service) {
        LOGGER.debug("DAO insert service {}", service);
        try (SqlSession sqlSession = getSession()) {
            try {
                getServiceMapper(sqlSession).insert(service);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert service {} {}", service, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return service;
    }

    @Override
    public Service getById(int id) {
        LOGGER.debug("DAO get service by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getServiceMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get service {}", id, ex);
            throw ex;
        }
    }

    @Override
    public Service getByName(String name) {
        LOGGER.debug("DAO get service by name {}", name);
        try (SqlSession sqlSession = getSession()) {
            return getServiceMapper(sqlSession).getByName(name);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get service {}", name, ex);
            throw ex;
        }
    }

    @Override
    public List<Service> getAll() {
        LOGGER.debug("DAO getAll service ");
        try (SqlSession sqlSession = getSession()) {
            return getServiceMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't getAll service", ex);
            throw ex;
        }
    }

    @Override
    public Integer getRevenueByMasterAndDate(Master master, LocalDate dateStart, LocalDate dateEnd) {
        LOGGER.debug("DAO getByMasterAndDateStartDateEnd revenue by master,dateStart,dateEnd {},{},{}",
                master, dateStart, dateEnd);
        try (SqlSession sqlSession = getSession()) {
            return getServiceMapper(sqlSession).getRevenueByMasterAndDate(master, dateStart, dateEnd);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't getByMasterAndDateStartDateEnd revenue " +
                    "by master,dateStart,dateEnd {},{},{}", master, dateStart, dateEnd, ex);
            throw ex;
        }
    }

    @Override
    public Integer getRevenueByDate(LocalDate dateStart, LocalDate dateEnd) {
        LOGGER.debug("DAO getByMasterAndDateStartDateEnd revenue by master,dateStart,dateEnd {},{}",
                dateStart, dateEnd);
        try (SqlSession sqlSession = getSession()) {
            return getServiceMapper(sqlSession).getRevenueByDate(dateStart, dateEnd);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't getByMasterAndDateStartDateEnd revenue " +
                    "by master,dateStart,dateEnd {},{}", dateStart, dateEnd, ex);
            throw ex;
        }
    }

    @Override
    public void deleteService(Service service) {
        LOGGER.debug("DAO deleteService service {}", service);
        try (SqlSession sqlSession = getSession()) {
            getServiceMapper(sqlSession).deleteService(service);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't deleteService service {}", service, ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All service");
        try (SqlSession sqlSession = getSession()) {
            try {
                getServiceMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all service", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }


}
