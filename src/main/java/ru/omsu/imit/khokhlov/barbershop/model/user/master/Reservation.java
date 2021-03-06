package ru.omsu.imit.khokhlov.barbershop.model.user.master;

import ru.omsu.imit.khokhlov.barbershop.model.user.Client;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reservation {
    private int id;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private DaySchedule daySchedule;
    private String receipt;
    private Client client;
    private List<Service> services;


    public Reservation(int id, DaySchedule daySchedule, LocalTime timeStart, LocalTime timeEnd, String receipt, Client client, List<Service> services) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.receipt = receipt;
        this.client = client;
        this.services = services;
        this.daySchedule = daySchedule;
    }

    public Reservation() {
    }

    public Reservation(DaySchedule daySchedule, LocalTime timeStart, LocalTime timeEnd, String receipt, Client client, List<Service> services) {
        this(0, daySchedule, timeStart, timeEnd, receipt, client, services);
    }

    public Reservation(DaySchedule daySchedule, LocalTime timeStart, LocalTime timeEnd, String receipt, List<Service> services) {
        this(0, daySchedule, timeStart, timeEnd, receipt, null, services);
    }

    public Reservation(DaySchedule daySchedule, LocalTime timeStart, LocalTime timeEnd, String receipt, Client client) {
        this(0, daySchedule, timeStart, timeEnd, receipt, client, new ArrayList<>());
    }

    public Reservation(DaySchedule daySchedule, LocalTime timeStart, LocalTime timeEnd, String receipt) {
        this(0, daySchedule, timeStart, timeEnd, receipt, null, new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isBooked() {
        return client != null;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public DaySchedule getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(DaySchedule daySchedule) {
        this.daySchedule = daySchedule;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return getId() == that.getId() &&
                Objects.equals(getTimeStart(), that.getTimeStart()) &&
                Objects.equals(getTimeEnd(), that.getTimeEnd()) &&
                Objects.equals(getReceipt(), that.getReceipt()) &&
                Objects.equals(getClient(), that.getClient()) &&
                Objects.equals(getServices(), that.getServices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTimeStart(), getTimeEnd(), getDaySchedule(), getReceipt(), getClient(), getServices());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", receipt='" + receipt + '\'' +
                ", client=" + client +
                ", services=" + services +
                '}';
    }
}
