package ru.omsu.imit.khokhlov.barbershop.model;

import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;


import static org.junit.jupiter.api.Assertions.assertAll;


public class UserTest extends BaseTest {
    @Test
    public void test1(){
        User user = new User("Иван","Иванов","Иванович",
                "login","password", UserType.ADMIN);
        User user1 =userDao.insert(user);
        User user2 = userDao.getById(user1.getId());
        User user3 = userDao.getByLogin("Login");
      assertAll(()-> Assert.assertEquals(user1,user2),
              ()->Assert.assertEquals(user1,user3));

}}

