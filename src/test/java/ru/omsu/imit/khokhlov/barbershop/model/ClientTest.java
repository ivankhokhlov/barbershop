package ru.omsu.imit.khokhlov.barbershop.model;

import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;

public class ClientTest extends BaseTest {
    @Test
    public void test1(){

        Client client = new Client(new User("Иван","Иванов","Иванович",
                "login","password", UserType.CLIENT),"example@gmail.com","Addres","88005553535");

        Client client1 = clientDao.insert(client);
        Client client2 = clientDao.getById(client1.getUser().getId());
        Assert.assertEquals(client1, client2);
    }
}
