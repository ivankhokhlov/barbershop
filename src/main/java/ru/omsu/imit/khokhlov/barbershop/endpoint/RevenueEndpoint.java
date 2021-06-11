package ru.omsu.imit.khokhlov.barbershop.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.response.RevenueResponse;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.RevenueService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@RestController
@RequestMapping("/api/revenues")
public class RevenueEndpoint {

    private final RevenueService revenueService;

    @Autowired
    public RevenueEndpoint(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping(value = "/{masterid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RevenueResponse getMasterRevenueByDate(@Positive @NotNull @PathVariable("masterid") Integer masterid,
                                                  @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "startDate") String startDate,
                                                  @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "endDate") String endDate,
                                                  HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return revenueService.getMasterRevenueByDate(masterid, startDate, endDate, uuid);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RevenueResponse getRevenueByDate(@DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "startDate") String startDate,
                                            @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam(required = false, name = "endDate") String endDate,
                                            HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return revenueService.getRevenueByDate(startDate, endDate, uuid);
    }

}
