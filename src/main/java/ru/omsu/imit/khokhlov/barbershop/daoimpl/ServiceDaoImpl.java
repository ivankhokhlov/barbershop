package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.ServiceDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

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
            LOGGER.info("Can't get service {}",id, ex);
            throw ex;
        }
    }

    @Override
    public Service getByName(String name) {
        LOGGER.debug("DAO get service by name {}", name);
        try (SqlSession sqlSession = getSession()) {
            return getServiceMapper(sqlSession).getByName(name);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get service {}",name, ex);
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
