package ru.omsu.imit.khokhlov.barbershop.dao;

import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

public interface SpecializationDao {
    Specialization insert(Specialization specialization);
    Specialization getById(int id);
    Specialization getByName(String name);
    void deleteAll();
}
