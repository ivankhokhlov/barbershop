package ru.omsu.imit.khokhlov.barbershop.dao;

import ru.omsu.imit.khokhlov.barbershop.model.user.User;

public interface UserDao {
    User insert(User user);
    User getById(int id);
    User getByLogin(String login);
    User getByLoginAndPassword(String login, String password);
    User update(User user);
    void deleteById(int id);
    void deleteAll();
}
