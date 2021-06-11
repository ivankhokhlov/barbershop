package ru.omsu.imit.khokhlov.barbershop.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.RecordToTheMasterRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.RecordToTheMasterResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ReservationResponse;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.ReservationService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptEndpoint {

    private final ReservationService reservationService;

    @Autowired
    public ReceiptEndpoint(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RecordToTheMasterResponse recordToTheDoctor(@Valid @RequestBody RecordToTheMasterRequest recordToTheMasterRequest,
                                                       HttpServletRequest request) {
        System.out.println(recordToTheMasterRequest);
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return reservationService.recordToTheMaster(recordToTheMasterRequest, uuid);
    }

    @GetMapping(value = "/{receipt}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationResponse getInfoByReceipt(@NotNull @PathVariable("receipt") String receipt,
                                                HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return reservationService.getInfoByReceipt(receipt, uuid);
    }

    @DeleteMapping(value = "/{receipt}")
    public String deleteRecordToTheDoctor(@NotNull @PathVariable("receipt") String receipt, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        reservationService.deleteRecordToTheMaster(receipt, uuid);
        return "{}";
    }

}

