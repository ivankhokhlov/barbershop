package ru.omsu.imit.khokhlov.barbershop.model;


import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;

public class CookieTest extends BaseTest {
    @Test
    public void test1() {
        User user=new User("Иван","Иванов","Иванович",
                "Admin21","password", UserType.ADMIN);
        userDao.insert(user);
        Cookie cookie1= new Cookie(user, UUID.randomUUID().toString());
        cookieDao.insert(cookie1);
        Cookie cookie2=cookieDao.getById(cookie1.getUser().getId());
        Cookie cookie3=cookieDao.getByUUID(cookie1.getUuid());
        cookieDao.deleteByUUID(cookie1.getUuid());
        Cookie cookie4=cookieDao.getByUUID(cookie1.getUuid());

        assertAll(()-> Assert.assertEquals(cookie1,cookie2),
                ()->Assert.assertEquals(cookie2,cookie3),
                ()->Assert.assertNull(cookie4));
    }
}
