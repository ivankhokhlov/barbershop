package ru.omsu.imit.khokhlov.barbershop.dao;

import ru.omsu.imit.khokhlov.barbershop.model.user.Client;

public interface ClientDao {
    Client insert(Client client);
    Client getById(int id);
    Client getByLogin(String login);
    Client update(Client client);
    void deleteAll();
}
