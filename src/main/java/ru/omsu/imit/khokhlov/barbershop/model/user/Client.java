package ru.omsu.imit.khokhlov.barbershop.model.user;

import java.util.Objects;

public class Client {
    private User user;
    private String email;
    private String address;
    private String phone;

    public Client() {
    }

    public Client(User user, String email, String address, String phone) {
        this.user = user;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getUser(), client.getUser()) &&
                Objects.equals(getEmail(), client.getEmail()) &&
                Objects.equals(getAddress(), client.getAddress()) &&
                Objects.equals(getPhone(), client.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getEmail(), getAddress(), getPhone());
    }

    @Override
    public String toString() {
        return "Client{" +
                "user=" + user +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
