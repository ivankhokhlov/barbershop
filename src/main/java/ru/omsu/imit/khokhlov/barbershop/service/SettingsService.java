package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.omsu.imit.khokhlov.barbershop.dto.response.AllSettingsResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.BaseSettingsResponse;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;

import javax.servlet.http.Cookie;


@Service
public class SettingsService extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsService.class);

    public BaseSettingsResponse getSettings() {
        LOGGER.debug("Service getSettings");
        try {

                return new BaseSettingsResponse(constraints.getMaxNameLength(), constraints.getMinPasswordLength());
        } catch (Exception ex) {
            LOGGER.info("Service can't getSettings", ex);
            throw ex;
        }
    }

}
