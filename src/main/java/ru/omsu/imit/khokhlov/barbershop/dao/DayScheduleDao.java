package ru.omsu.imit.khokhlov.barbershop.dao;

import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;

import java.time.LocalDate;
import java.util.List;

public interface DayScheduleDao {
    DaySchedule insert(DaySchedule daySchedule);
    DaySchedule getById(int id);
    List<DaySchedule> getByMasterAndDate(Master master, LocalDate dateStart, LocalDate dateEnd);
    List<DaySchedule> getByDate(LocalDate dateStart,LocalDate dateEnd);
    void delete(DaySchedule daySchedule);
    void deleteAll();
}
