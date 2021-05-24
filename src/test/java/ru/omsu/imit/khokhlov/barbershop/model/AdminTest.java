package ru.omsu.imit.khokhlov.barbershop.model;


import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.Admin;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;
import ru.omsu.imit.khokhlov.barbershop.utils.MyBatisUtils;

import static org.junit.jupiter.api.Assertions.assertAll;

public class AdminTest extends BaseTest {
    @Test
    public void test1(){

        Admin admin= new Admin(new User("Иван","Иванов","Иванович",
                "Admin2","password", UserType.ADMIN),"Главный программист");
        Admin admin1=adminDao.insert(admin);
        Admin admin2= adminDao.getById(admin.getUser().getId());
        Admin admin3= adminDao.getByLogin("Admin2");
        Assert.assertEquals(admin1,admin2);
        assertAll(()->Assert.assertEquals(admin1,admin2),
        ()->Assert.assertEquals(admin2,admin3));

    }

}
