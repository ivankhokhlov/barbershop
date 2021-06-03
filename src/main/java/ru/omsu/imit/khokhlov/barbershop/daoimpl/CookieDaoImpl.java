package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.khokhlov.barbershop.dao.CookieDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;

public class CookieDaoImpl extends BaseDaoImpl implements CookieDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);

    @Override
    public Cookie insert(Cookie cookie) {
        LOGGER.debug("DAO insert cookie {}", cookie);
        try (SqlSession sqlSession = getSession()) {
            try {
                getCookieMapper(sqlSession).insert(cookie);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert cookie {} {}", cookie, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return cookie;
    }

    @Override
    public Cookie getById(int id) {
        LOGGER.debug("DAO get Cookie by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getCookieMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Cookie {}",id ,ex);
            throw ex;
        }
    }

    @Override
    public Cookie getByUUID(String uuid) {
        LOGGER.debug("DAO get Cookie by uuid {}", uuid);
        try (SqlSession sqlSession = getSession()) {
            return getCookieMapper(sqlSession).getByUUID(uuid);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Cookie {}",uuid, ex);
            throw ex;
        }
    }

    @Override
    public void deleteByUUID(String uuid) {
        LOGGER.debug("DAO delete Cookie by uuid {}", uuid);
        try (SqlSession sqlSession = getSession()) {
            try {
                getCookieMapper(sqlSession).deleteByUUID(uuid);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Cookie by uuid {}",uuid,ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
    @Override
    public void deleteById(int id) {
        LOGGER.debug("DAO delete Cookie by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                getCookieMapper(sqlSession).deleteById(id);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Cookie by uuid {}",id,ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Cookie");
        try (SqlSession sqlSession = getSession()) {
            try {
                getCookieMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete All Cookie", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
