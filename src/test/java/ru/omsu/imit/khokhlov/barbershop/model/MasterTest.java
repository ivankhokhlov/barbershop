package ru.omsu.imit.khokhlov.barbershop.model;

import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.UserType;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MasterTest extends BaseTest {
    @Test
    public void test1() {
        Client client = new Client(new User("Иван","Иванов","Иванович",
                "client","password", UserType.CLIENT),"example@gmail.com","Addres","88005553535");

        Client client1 = clientDao.insert(client);
        Service service = serviceDao.insert(new Service("Короткая стрижка",200));
        Service service1 = serviceDao.insert(new Service("Средняя стрижка",250));
        Service service2 = serviceDao.insert( new Service("Длинная  стрижка",150));
        List<Service> services= new ArrayList<>(Arrays.asList(
                service,service1,service2));
        Specialization specialization = new Specialization("Парикмахер");
        Specialization specialization1 = specializationDao.insert(specialization);
        Master master = new Master(new User("Мастер","Мастеров","Мастервич",
                "master","password", UserType.MASTER),specialization1,services);
        Master master1 = masterDao.insert(master);
        DaySchedule daySchedule= new DaySchedule(master1,LocalDate.of(2021,4,1));
        DaySchedule daySchedule1= dayScheduleDao.insert(daySchedule);

        Service service4 = serviceDao.insert(new Service("Мытье головы",100));
        Service service5 = serviceDao.insert(new Service("Короткая стрижка",200));
        Reservation reservation = new Reservation(daySchedule1, LocalTime.of(8,0,0),
                LocalTime.of(9,0,0),"ticket",client1,new ArrayList<>(Arrays.asList(service4,service5
        )));
        Reservation reservation1 = reservationDao.insert(reservation);
        daySchedule1.getReservations().add(reservation1);
        master1.getDaySchedulesList().add(daySchedule1);
        master1.setService(services);
        Master master2= masterDao.getById(master1.getUser().getId());

        Assert.assertEquals(master1, master2);


    }
}
