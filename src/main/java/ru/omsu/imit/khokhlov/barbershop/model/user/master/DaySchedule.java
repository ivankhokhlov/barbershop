package ru.omsu.imit.khokhlov.barbershop.model.user.master;

import ru.omsu.imit.khokhlov.barbershop.model.user.Master;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DaySchedule {
    private int id;
    private Master master;
    private LocalDate curDate;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private List<Reservation> reservations;

    public DaySchedule(int id, Master master, LocalDate curDate,
                       LocalTime timeStart, LocalTime timeEnd, List<Reservation> reservations) {
        this.id = id;
        this.master = master;
        this.curDate = curDate;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.reservations = reservations;
    }

    public DaySchedule() {
    }

    public DaySchedule(Master master,LocalDate curDate,LocalTime timeStart, LocalTime timeEnd) {
        this(0,master,curDate,timeStart,timeEnd,new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCurDate() {
        return curDate;
    }

    public void setCurDate(LocalDate curDate) {
        this.curDate = curDate;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DaySchedule)) return false;
        DaySchedule that = (DaySchedule) o;
        return getId() == that.getId() &&
                Objects.equals(getCurDate(), that.getCurDate()) &&
                Objects.equals(getTimeStart(), that.getTimeStart()) &&
                Objects.equals(getTimeEnd(), that.getTimeEnd()) &&
                Objects.equals(getReservations(), that.getReservations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCurDate(), getTimeStart(), getTimeEnd(), getReservations());
    }

    @Override
    public String toString() {
        return "DaySchedule{" +
                "id=" + id +
                ", curDate=" + curDate +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", reservations=" + reservations +
                '}';
    }
}
