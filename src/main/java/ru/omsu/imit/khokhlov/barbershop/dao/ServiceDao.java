package ru.omsu.imit.khokhlov.barbershop.dao;

import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

public interface ServiceDao {
    Service insert(Service service);
    Service getById(int id);
    Service getByName(String name);
    void deleteAll();
}
