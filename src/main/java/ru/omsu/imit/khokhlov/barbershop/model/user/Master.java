package ru.omsu.imit.khokhlov.barbershop.model.user;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Master {
    private User user;
    private Specialization specialization;
    private List<Service> service;
    private List<DaySchedule> daySchedulesList;

    public Master(User user, Specialization specialization,  List<Service> service, List<DaySchedule> daySchedulesList) {
        this.user = user;
        this.specialization = specialization;
        this.service = service;
        this.daySchedulesList = daySchedulesList;
    }

    public Master() {
    }

    public Master(User user, Specialization specialization,  List<Service> service) {
        this(user, specialization, service,new ArrayList<>());
    }
    public Master(User user, Specialization specialization) {
        this(user, specialization,new ArrayList<>(),new ArrayList<>());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public List<DaySchedule> getDaySchedulesList() {
        return daySchedulesList;
    }

    public void setDaySchedulesList(List<DaySchedule> daySchedulesList) {
        this.daySchedulesList = daySchedulesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Master)) return false;
        Master master = (Master) o;
        return Objects.equals(getUser(), master.getUser()) &&
                Objects.equals(getSpecialization(), master.getSpecialization()) &&
                Objects.equals(getService(), master.getService()) &&
                Objects.equals(getDaySchedulesList(), master.getDaySchedulesList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getSpecialization(), getService(),getDaySchedulesList());
    }

    @Override
    public String toString() {
        return "Master{" +
                "user=" + user +
                ", specialization=" + specialization +
                ", service=" + service +
                ", daySchedulesList=" + daySchedulesList +
                '}';
    }
}
