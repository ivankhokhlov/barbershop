package ru.omsu.imit.khokhlov.barbershop.service;

import ru.omsu.imit.khokhlov.barbershop.configuration.Constraints;
import ru.omsu.imit.khokhlov.barbershop.dao.*;
import ru.omsu.imit.khokhlov.barbershop.daoimpl.*;

public class BaseService {
    protected static UserDao userDao= new UserDaoImpl();
    protected static AdminDao adminDao= new AdminDaoImpl();
    protected static ServiceDao serviceDao = new ServiceDaoImpl();
    protected static SpecializationDao specializationDao = new SpecializationDaoImpl();
    protected static ClientDao clientDao = new ClientDaoImpl();
    protected static DayScheduleDao dayScheduleDao= new DayScheduleDaoImpl();
    protected static MasterDao masterDao =new MasterDaoImpl();
    protected static ReservationDao reservationDao =new ReservationDaoImpl();
    protected static CookieDao cookieDao = new CookieDaoImpl();
    protected static Constraints constraints= new Constraints();
    protected static ResponseProcessor responseProcessor = new ResponseProcessor();

    public BaseService() {
    }

}
