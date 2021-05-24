package ru.omsu.imit.khokhlov.barbershop.dao;

import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;

public interface CookieDao {
    Cookie insert(Cookie cookie);
    Cookie getById(int id);
    Cookie getByUUID(String uuid);
    void deleteByUUID(String uuid);
    void deleteAll();

}
