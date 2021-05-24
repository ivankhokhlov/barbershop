package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.SpecializationDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

@Component
public class SpecializationDaoImpl extends BaseDaoImpl implements SpecializationDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecializationDaoImpl.class);
    @Override
    public Specialization insert(Specialization specialization) {
        LOGGER.debug("DAO insert specialization {}", specialization);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSpecializationMapper(sqlSession).insert(specialization);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert specialization {} {}", specialization, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return specialization;
    }

    @Override
    public Specialization getById(int id) {
        LOGGER.debug("DAO get specialization by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getSpecializationMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get specialization {}",id, ex);
            throw ex;
        }
    }

    @Override
    public Specialization getByName(String name) {
        LOGGER.debug("DAO get specialization by name {}", name);
        try (SqlSession sqlSession = getSession()) {
            return getSpecializationMapper(sqlSession).getByName(name);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get specialization {}", name);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All specialization");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSpecializationMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all specialization", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
