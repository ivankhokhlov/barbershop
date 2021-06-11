package ru.omsu.imit.khokhlov.barbershop.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.ServicesRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.MasterInfoWithoutScheduleResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ServiceResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ServiceResponseAndMastersId;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.ServiceService;
import ru.omsu.imit.khokhlov.barbershop.service.UpdateService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceEndpoint {

    private final UpdateService updateService;
    private final ServiceService serviceService;

    @Autowired
    public ServiceEndpoint(UpdateService updateService, ServiceService serviceService) {
        this.updateService = updateService;
        this.serviceService = serviceService;
    }

    @GetMapping(value = "/{masterid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceResponse> getMasterService(@Positive @NotNull @PathVariable("masterid") Integer masterid, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return serviceService.getServicesFromMaster(masterid, uuid);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceResponseAndMastersId> getMasterService(HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return serviceService.getServices(uuid);
    }

    @PutMapping(value = "/{masterid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MasterInfoWithoutScheduleResponse addServicesToMaster(@Positive @NotNull @PathVariable("masterid") Integer masterid,
                                                                 @Valid @RequestBody ServicesRequest servicesRequest, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return updateService.addServices(masterid, servicesRequest, uuid);
    }

    @DeleteMapping(value = "/{masterid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MasterInfoWithoutScheduleResponse deleteServicesToMaster(@Positive @NotNull @PathVariable("masterid") Integer masterid,
                                                                    @Valid @RequestBody ServicesRequest servicesRequest, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return serviceService.deleteServices(masterid, servicesRequest, uuid);
    }
}
