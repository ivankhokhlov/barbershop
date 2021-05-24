package ru.omsu.imit.khokhlov.barbershop.model.user;

import java.util.Objects;

public class Admin{
    private User user;
    private String position;

    public Admin() {
    }

    public Admin(User user, String position) {
        this.user = user;
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(getUser(), admin.getUser()) &&
                Objects.equals(getPosition(), admin.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getPosition());
    }

    @Override
    public String toString() {
        return "Admin{" +
                "user=" + user +
                ", position='" + position + '\'' +
                '}';
    }
}
