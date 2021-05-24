package ru.omsu.imit.khokhlov.barbershop.dao;


import ru.omsu.imit.khokhlov.barbershop.model.user.Admin;

public interface AdminDao {
    Admin insert(Admin admin);
    Admin getById(int id);
    Admin getByLogin(String login);
    Admin update(Admin admin);
    void deleteAll();
}
