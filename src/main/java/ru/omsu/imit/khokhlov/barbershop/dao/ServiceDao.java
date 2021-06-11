package ru.omsu.imit.khokhlov.barbershop.dao;


import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

import java.time.LocalDate;
import java.util.List;

public interface ServiceDao {
    Service insert(Service service);

    Service getById(int id);

    Service getByName(String name);

    Integer getRevenueByMasterAndDate(Master master, LocalDate dateStart, LocalDate dateEnd);

    Integer getRevenueByDate(LocalDate dateStart, LocalDate dateEnd);

    List<Service> getAll();

    void deleteService(Service service);

    void deleteAll();
}
