package ru.omsu.imit.khokhlov.barbershop.model.user.master;

import java.util.Objects;

public class Specialization {
    private  int id ;
    private String name;

    public Specialization(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Specialization(String name) {
        this(0,name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialization that = (Specialization) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
