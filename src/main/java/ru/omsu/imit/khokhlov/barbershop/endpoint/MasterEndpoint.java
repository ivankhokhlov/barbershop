package ru.omsu.imit.khokhlov.barbershop.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.RegisterMasterRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.UpdateMasterRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.UpdateScheduleRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.MasterInfoWithoutScheduleResponse;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.RegisterService;
import ru.omsu.imit.khokhlov.barbershop.service.ScheduleService;
import ru.omsu.imit.khokhlov.barbershop.service.UpdateService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/api/masters")
public class MasterEndpoint {
    private final RegisterService registerService;
    private final UpdateService updateService;
    private final ScheduleService scheduleService;


    @Autowired
    public MasterEndpoint(RegisterService registerService, UpdateService updateService, ScheduleService scheduleService) {
        this.registerService = registerService;
        this.updateService = updateService;
        this.scheduleService = scheduleService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object registerMaster(@Valid @RequestBody RegisterMasterRequest registerMasterRequest, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return registerService.registerMaster(registerMasterRequest, uuid);
    }

    @PutMapping(value = "/{masterid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MasterInfoWithoutScheduleResponse updateMaster(@Positive @NotNull @PathVariable("masterid") Integer masterid,
                                                          @Valid @RequestBody UpdateMasterRequest updateMasterRequest, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return updateService.updateMaster(masterid, updateMasterRequest, uuid);
    }

    @PutMapping(value = "/schedule/{masterid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MasterInfoWithoutScheduleResponse updateSchedule(@Positive @NotNull @PathVariable("masterid") Integer masterid,
                                                            @Valid @RequestBody UpdateScheduleRequest updateScheduleRequest, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return updateService.updateSchedule(masterid, updateScheduleRequest, uuid);
    }


    @GetMapping(value = "/{masterid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MasterInfoWithoutScheduleResponse getMasterSchedule(@Positive @NotNull @PathVariable("masterid") Integer masterid,
                                                               @RequestParam(required = false, name = "schedule") String schedule,
                                                               @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "startDate") String startDate,
                                                               @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "endDate") String endDate
            , HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return scheduleService.getMasterSchedule(masterid, schedule, startDate, endDate, uuid);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MasterInfoWithoutScheduleResponse> getMastersSchedule(@RequestParam(required = false, name = "schedule") String schedule,
                                                                      @RequestParam(required = false, name = "specialization ") String specialization,
                                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "startDate") String startDate,
                                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "endDate") String endDate
            , HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        System.out.println(specialization);
        return scheduleService.getMastersSchedule(schedule, specialization, startDate, endDate, uuid);
    }
}
