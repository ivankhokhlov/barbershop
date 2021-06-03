package ru.omsu.imit.khokhlov.barbershop.dao;

import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

import java.util.List;

public interface MasterDao {
    Master insert(Master master);
    Master getById(int id);
    List<Master> getBySpecialization(Specialization specialization);
    Master getByReservation(Reservation reservation);
    List<Master> getAllMaster();
    void deleteAll();
}
