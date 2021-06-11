package ru.omsu.imit.khokhlov.barbershop.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.omsu.imit.khokhlov.barbershop.dto.request.RegisterAdminRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.UpdateAdminRequest;
import ru.omsu.imit.khokhlov.barbershop.service.CookieProcessor;
import ru.omsu.imit.khokhlov.barbershop.service.RegisterService;
import ru.omsu.imit.khokhlov.barbershop.service.UpdateService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admins")
public class AdminEndpoint {
    private final RegisterService registerService;
    private final UpdateService updateService;

    @Autowired
    public AdminEndpoint(RegisterService registerService, UpdateService updateService) {
        this.registerService = registerService;
        this.updateService = updateService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object registerAdmin(@Valid @RequestBody RegisterAdminRequest registerAdminRequest, HttpServletRequest request) {
        Cookie cookie = CookieProcessor.getCookie(request);
        String uuid = cookie.getValue();
        return registerService.registerAdmin(registerAdminRequest, uuid);
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object updateAdmin(@Valid @RequestBody UpdateAdminRequest updateAdminRequest, HttpServletRequest request) {
        Cookie cookie= CookieProcessor.getCookie(request);
        String uuid=cookie.getValue();
        return updateService.updateAdmin(updateAdminRequest,uuid);
    }
}
