package ru.omsu.imit.khokhlov.barbershop.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omsu.imit.khokhlov.barbershop.dto.response.BaseSettingsResponse;
import ru.omsu.imit.khokhlov.barbershop.service.SettingsService;


@RestController
@RequestMapping("/api/settings")
public class SettingsEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsEndpoint.class);
    private SettingsService settingsService;

    @Autowired
    public SettingsEndpoint(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseSettingsResponse getSettings() {
        return settingsService.getSettings();
    }
}

