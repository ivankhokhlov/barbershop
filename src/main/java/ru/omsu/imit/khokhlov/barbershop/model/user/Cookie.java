package ru.omsu.imit.khokhlov.barbershop.model.user;

import java.util.Objects;

public class Cookie {
    private User user;
    private String uuid;

    public Cookie(User user, String uuid) {
        this.user = user;
        this.uuid = uuid;
    }

    public Cookie() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



    @Override
    public String toString() {
        return "Cookie{" +
                "user=" + user +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cookie)) return false;
        Cookie cookie = (Cookie) o;
        return Objects.equals(getUser(), cookie.getUser()) &&
                Objects.equals(getUuid(), cookie.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getUuid());
    }
}
