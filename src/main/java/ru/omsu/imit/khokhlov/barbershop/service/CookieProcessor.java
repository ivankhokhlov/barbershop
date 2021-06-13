package ru.omsu.imit.khokhlov.barbershop.service;

import org.springframework.stereotype.Service;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieProcessor {

    public static void deleteCookie(Cookie cookie){
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
    }
    public static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookieName = "SESSIONID";
        Cookie cookie = null;
        if (cookies == null) {
            throw new ServerException(ErrorCodes.INVALID_COOKIE);
        }
        for (Cookie c : cookies) {
            if (cookieName.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        if (cookie == null) {
            throw new ServerException(ErrorCodes.INVALID_COOKIE);
        }
        String regex= "^[0-9A-Fa-f]{8}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{12}$";
        String uuid =cookie.getValue();
        if(!uuid.matches(regex)){
            throw new ServerException(ErrorCodes.INVALID_JAVA_SESSION_ID);
        }
        return cookie;
    }
    public static Cookie getCookieWithoutChecks(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String cookieName = "SESSIONID";
        Cookie cookie = null;
        if (cookies != null) {

        for (Cookie c : cookies) {
            if (cookieName.equals(c.getName())) {
                cookie = c;
                break;
            }
        }}
        return cookie;
    }
}
