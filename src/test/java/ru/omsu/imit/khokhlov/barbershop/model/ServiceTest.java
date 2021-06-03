package ru.omsu.imit.khokhlov.barbershop.model;

import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

import static org.junit.jupiter.api.Assertions.assertAll;

public class ServiceTest extends BaseTest{
    @Test
    public void test1(){
        Service service = new Service("Модельная стрижка",12,20);
        Service service1 = serviceDao.insert(service);
        Service service2 = serviceDao.getById(service1.getId());
        Service service3 = serviceDao.getByName(service1.getName());
        assertAll(()-> Assert.assertEquals(service1, service2),
                ()->Assert.assertEquals(service2, service3));
    }
}
