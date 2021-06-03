package ru.omsu.imit.khokhlov.barbershop.model.user.master;

import java.util.Objects;

public class Service {
    private int id;
    private String name;
    private int price;
    private int duration;

    public Service() {
    }

    public Service(int id, String name, int price, int duration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public Service(String name, int price, int duration) {
        this(0,name,price,duration);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return getId() == service.getId() &&
                getPrice() == service.getPrice() &&
                getDuration() == service.getDuration() &&
                Objects.equals(getName(), service.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getDuration());
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
