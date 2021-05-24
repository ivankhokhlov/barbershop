package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.AdminDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.Admin;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;

@Component
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);

    @Override
    public Admin insert(Admin admin) {
        LOGGER.debug("DAO insert admin {}", admin);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(admin.getUser());
                getAdminMapper(sqlSession).insert(admin);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert admin {} {}", admin, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return admin;
    }

    @Override
    public Admin update(Admin admin) {
        LOGGER.debug("DAO update admin {}", admin);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(admin.getUser());
                getAdminMapper(sqlSession).update(admin);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update admin {} {}", admin, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return admin;
    }

    @Override
    public Admin getById(int id) {
        LOGGER.debug("DAO get admin by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getAdminMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get admin {}",id, ex);
            throw ex;
        }
    }

    @Override
    public Admin getByLogin(String login) {
        LOGGER.debug("DAO get Admin by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            User user = getUserMapper(sqlSession).getByLogin(login);
            Admin admin = getAdminMapper(sqlSession).getById(user.getId());
            admin.setUser(user);
            return admin;
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Admin {}",login, ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All admin");
        try (SqlSession sqlSession = getSession()) {
            try {
                getAdminMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all admin", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }


}
