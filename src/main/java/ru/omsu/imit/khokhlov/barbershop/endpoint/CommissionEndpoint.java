package ru.omsu.imit.khokhlov.barbershop.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/commissions")
public class CommissionEndpoint {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CommissionEndpoint.class);
//    private Service service;
//
//    @Autowired
//    public CommissionEndpoint(Service service) {
//        this.service = service;
//    }
//
//
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ConveneCommissionResponse conveneCommission(@Valid @RequestBody ConveneCommissionRequest conveneCommissionRequest,
//                                                       HttpServletRequest request) {
//        Cookie cookie = CookieProssesor.getCookie(request);
//        String uuid = cookie.getValue();
//        return service.conveneCommission(conveneCommissionRequest, uuid);
//    }
//
//    @DeleteMapping(value = "/{ticket}")
//    public String deleteCommission(@NotNull @PathVariable("ticket") String ticket, HttpServletRequest request) {
//        Cookie cookie = CookieProssesor.getCookie(request);
//        String uuid = cookie.getValue();
//        service.deleteCommission(ticket,uuid);
//        return "{}";
//    }

}