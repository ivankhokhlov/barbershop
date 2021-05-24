package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.ClientDao;

import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;

@Component
public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDaoImpl.class);
    @Override
    public Client insert(Client client) {
        LOGGER.debug("DAO insert client {}", client);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(client.getUser());
                getClientMapper(sqlSession).insert(client);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert client {} {}", client, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return client;
    }

    @Override
    public Client update(Client client) {
        LOGGER.debug("DAO update client {}", client);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(client.getUser());
                getClientMapper(sqlSession).update(client);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update client {} {}", client, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return client;
    }

    @Override
    public Client getById(int id) {
        LOGGER.debug("DAO get client by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            User user= getUserMapper(sqlSession).getById(id);
            Client client =getClientMapper(sqlSession).getById(id);
            return new Client(user, client.getEmail(), client.getAddress(), client.getPhone());
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get client {}",id, ex);
            throw ex;
        }
    }
    @Override
    public Client getByLogin(String login) {
        LOGGER.debug("DAO get client by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            User user=getUserMapper(sqlSession).getByLogin(login);
            Client client =getClientMapper(sqlSession).getById(user.getId());
            client.setUser(user);
            return client;
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get client {}",login, ex);
            throw ex;
        }
    }
    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All client");
        try (SqlSession sqlSession = getSession()) {
            try {
                getClientMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all client", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();

        }
    }
}