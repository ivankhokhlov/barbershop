package ru.omsu.imit.khokhlov.barbershop.model;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.omsu.imit.khokhlov.barbershop.dao.*;
import ru.omsu.imit.khokhlov.barbershop.daoimpl.*;
import ru.omsu.imit.khokhlov.barbershop.utils.MyBatisUtils;

public class BaseTest {
    protected static UserDao userDao= new UserDaoImpl();
    protected static AdminDao adminDao= new AdminDaoImpl();
    protected static ServiceDao serviceDao = new ServiceDaoImpl();
    protected static SpecializationDao specializationDao = new SpecializationDaoImpl();
    protected static ClientDao clientDao = new ClientDaoImpl();
    protected static DayScheduleDao dayScheduleDao= new DayScheduleDaoImpl();
    protected static MasterDao masterDao =new MasterDaoImpl();
    protected static ReservationDao reservationDao =new ReservationDaoImpl();
    protected static CookieDao cookieDao = new CookieDaoImpl();

    @BeforeClass
    public static void init() {
        Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
    }

    @Before
    public void deleteAll() {
        userDao.deleteAll();
        adminDao.deleteAll();
        serviceDao.deleteAll();
        specializationDao.deleteAll();
        clientDao.deleteAll();
        dayScheduleDao.deleteAll();
        masterDao.deleteAll();
        reservationDao.deleteAll();
        cookieDao.deleteAll();
    }

}
