package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.UserDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;

@Component
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public User insert(User user) {
        LOGGER.debug("DAO insert user {}", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(user);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert user {} {}", user, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return user;
    }
    @Override
    public User update(User user) {
        LOGGER.debug("DAO update user {}", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(user);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update user {} {}", user, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return user;
    }


    @Override
    public User getById(int id) {
        LOGGER.debug("DAO get User by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getUserMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get User {}",id,ex);
            throw ex;
        }
    }

    @Override
    public User getByLogin(String login) {
        LOGGER.debug("DAO get User by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            return getUserMapper(sqlSession).getByLogin(login);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get User {}",login, ex);
            throw ex;
        }
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        LOGGER.debug("DAO get User by login,password {},{}", login,password);
        try (SqlSession sqlSession = getSession()) {
            return getUserMapper(sqlSession).getByLoginAndPassword(login,password);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get User {},{}",login,password, ex);
            throw ex;
        }
    }
    @Override
    public void deleteById(int id) {
        LOGGER.debug("DAO Delete user by id {}",id);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteById(id);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete user by id {}",id, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All user");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all user", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
