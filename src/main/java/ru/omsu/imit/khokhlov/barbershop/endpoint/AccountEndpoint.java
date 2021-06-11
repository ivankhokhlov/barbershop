package ru.omsu.imit.khokhlov.barbershop.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.SessionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/account")
public class AccountEndpoint {
    private final SessionService sessionService;

    @Autowired
    public AccountEndpoint(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getInfo(HttpServletRequest request) {
            Cookie cookie= CookieProcessor.getCookie(request);
            String uuid=cookie.getValue();
            return sessionService.getInfo(uuid);
    }

}
