package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.omsu.imit.khokhlov.barbershop.dto.request.LoginRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ClientInfoResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.util.TransferResponseAndCookie;
import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.util.UUID;

@Service
public class SessionService extends BaseService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    public TransferResponseAndCookie login(LoginRequest request) {
        LOGGER.debug("Service login user {}", request);
        try {
            if (userDao.getByLogin(request.getLogin()) == null) {
                throw new ServerException(ErrorCodes.USER_NOT_FOUND);
            }
            User user = userDao.getByLoginAndPassword(request.getLogin(), request.getPassword());
            if (user == null) {
                throw new ServerException(ErrorCodes.WRONG_PASSWORD);
            }

            Cookie cookie = new Cookie(userDao.getById(user.getId()), UUID.randomUUID().toString());
            TransferResponseAndCookie transferResponseAndCookie = new TransferResponseAndCookie(cookie.getUuid());
            if (cookieDao.getById(user.getId()) != null) {
                cookieDao.deleteById(user.getId());
            }
            cookieDao.insert(cookie);
            transferResponseAndCookie.setResponse(responseProcessor.getResponse(user));
            return transferResponseAndCookie;
        } catch (Exception ex) {
            LOGGER.info("Service can't login user ", ex);
            throw ex;
        }
    }

    public void logout(String uuid) {
        LOGGER.debug("Service logout by uuid {}", uuid);
        try {
            cookieDao.deleteByUUID(uuid);
        } catch (Exception ex) {
            LOGGER.info("Service can't logout user ", ex);
            throw ex;
        }
    }
    public Object getInfo(String uuid) {
        LOGGER.debug("Service getInfo by uuid{}", uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            return responseProcessor.getResponse(cookie.getUser());
        } catch (Exception ex) {
            LOGGER.info("Service can't getInfo", ex);
            throw ex;
        }
    }
    public ClientInfoResponse getClientInfo(int id, String uuid) {
        LOGGER.debug("Service getClientInfo id,uuid{}", uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            responseProcessor.checkAdminOrMasterPermission(cookie.getUser());
            Client client = clientDao.getById(id);
            if (client == null) {
                throw new ServerException(ErrorCodes.WRONG_PASSWORD);
            }
            if (client.getUser().getUserType() != UserType.CLIENT) {
                throw new ServerException(ErrorCodes.INVALID_CLIENT_ID);
            }
            return responseProcessor.getResponse(client);
        } catch (Exception ex) {
            LOGGER.info("Service can't getInfo", ex);
            throw ex;
        }
    }



}
