package ru.omsu.imit.khokhlov.barbershop.dao;


import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationDao {
    Reservation insert(Reservation reservation);
    Reservation getById(int id);
    Reservation updateClient(Reservation reservation);
    Reservation getByDateTimeAndMaster(LocalDate date, LocalTime time, Master master);
    List<Reservation> getByDateTimeStartTimeEndAndMaster(LocalDate date, LocalTime timeStart,
                                                           LocalTime timeEnd, Master master);
    Reservation getByReceipt(String receipt);
    void deleteByReceipt(String receipt);
    void deleteAll();
}
