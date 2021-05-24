package ru.omsu.imit.khokhlov.barbershop.model;

import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

import static org.junit.jupiter.api.Assertions.assertAll;

public class SpecializationTest extends BaseTest {
    @Test
    public void test1() {
        Specialization specialization = new Specialization("Парикмахер");
        Specialization specialization1 = specializationDao.insert(specialization);
        Specialization specialization2 = specializationDao.getById(specialization1.getId());
        Specialization specialization3 = specializationDao.getByName(specialization1.getName());
        assertAll(()-> Assert.assertEquals(specialization1, specialization2),
                ()->Assert.assertEquals(specialization2, specialization3));
    }

}
