package ru.omsu.imit.khokhlov.barbershop.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.LoginRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.RegisterClientRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.UpdateClientRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ClientInfoResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.util.TransferResponseAndCookie;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.RegisterService;
import ru.omsu.imit.khokhlov.barbershop.service.SessionService;
import ru.omsu.imit.khokhlov.barbershop.service.UpdateService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/clients")
public class ClientEndpoint {
    private final RegisterService registerService;
    private final UpdateService updateService;
    private final SessionService sessionService;

    @Autowired
    public ClientEndpoint(RegisterService registerService, UpdateService updateService, SessionService sessionService) {
        this.registerService = registerService;
        this.updateService = updateService;
        this.sessionService = sessionService;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object registerClient(@Valid @RequestBody RegisterClientRequest registerClientRequest, HttpServletResponse response) {
        registerService.registerClient(registerClientRequest);
        TransferResponseAndCookie transferResponseAndCookie = sessionService.login(
                new LoginRequest(registerClientRequest.getLogin()
                        , registerClientRequest.getPassword()));

        response.addCookie(new Cookie("SESSIONID", transferResponseAndCookie.getCookie()));
        return transferResponseAndCookie.getResponse();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object updateClient(@Valid @RequestBody UpdateClientRequest updateClientRequest, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return updateService.updateClient(updateClientRequest, uuid);

    }

    @GetMapping(value = "/{clientid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientInfoResponse getClientInfo(@Positive @NotNull @PathVariable("clientid") Integer clientid, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return sessionService.getClientInfo(clientid, uuid);

    }

}
