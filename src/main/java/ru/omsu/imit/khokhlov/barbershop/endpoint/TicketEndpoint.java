package ru.omsu.imit.khokhlov.barbershop.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.RecordToTheMasterRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.RecordToTheMasterResponse;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.ReservationService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/tickets")
public class TicketEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketEndpoint.class);
    private ReservationService reservationService;
    @Autowired
    public TicketEndpoint(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public RecordToTheMasterResponse recordToTheDoctor(@Valid @RequestBody RecordToTheMasterRequest recordToTheMasterRequest ,
                                                       HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return reservationService.recordToTheMaster(recordToTheMasterRequest,uuid);
    }

    @DeleteMapping(value = "/{ticket}")
    public String deleteRecordToTheDoctor(@NotNull @PathVariable("ticket") String ticket, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        reservationService.deleteRecordToTheMaster(ticket,uuid);
        return "{}";
    }

}

