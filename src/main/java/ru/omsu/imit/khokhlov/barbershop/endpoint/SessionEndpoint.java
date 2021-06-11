package ru.omsu.imit.khokhlov.barbershop.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.LoginRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.util.TransferResponseAndCookie;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.SessionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/sessions")
public class SessionEndpoint {
    private final SessionService service;

    @Autowired
    public SessionEndpoint(SessionService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        TransferResponseAndCookie transferResponseAndCookie=service.login(loginRequest);
        response.addCookie(new Cookie("JAVASESSIONID",transferResponseAndCookie.getCookie()));
        return transferResponseAndCookie.getResponse();
    }
    @DeleteMapping
    public String logout(HttpServletRequest request, HttpServletResponse response ) {
        Cookie cookie= CookieProcessor.getCookie(request);
        String uuid=cookie.getValue();
        service.logout(uuid);
        CookieProcessor.deleteCookie(cookie);
        response.addCookie(cookie);
        return "{}";
    }


}
