package ru.omsu.imit.khokhlov.barbershop.model;

import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationTest extends BaseTest {
    @Test
    public void test1() {

    }
}
//    User user= new User("Иван","Иванов","Иванович",
//            "login","password", UserType.DOCTOR);
//    Speciality speciality=new Speciality("Терапевт");
//    Room room= new Room("21");
//    Speciality speciality1 =specialityDao.insert(speciality);
//    Room room1=roomDao.insert(room);
//    Doctor doctor= new Doctor(user,speciality1,room1);
//    Doctor doctor1=doctorDao.insert(doctor);
//    DaySchedule daySchedule = new DaySchedule(
//            LocalDate.of(2020,1,1)
//    );
//    DaySchedule daySchedule1 = dayScheduleDao.insert(daySchedule,doctor1);
//
//    Reception reception= new Reception(
//            LocalTime.of(9,0),LocalTime.of(9,15),"111");
//    Reception reception1=receptionDao.insert(reception,daySchedule1);
//        daySchedule1.getReceptions().add(reception1);
//                doctor1.getDaySchedulesList().add(daySchedule1);
//                Assert.assertEquals(doctorDao.getById(doctor1.getUser().getId()),doctor1);
//
