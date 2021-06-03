package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.util.Objects;

public class ClientInfoResponse {
    private int clientId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String address;
    private String phone;
    public ClientInfoResponse() {
    }

    public ClientInfoResponse(Integer clientId, String firstName, String lastName,
                              String patronymic, String email, String address, String phone) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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
        if (!(o instanceof ClientInfoResponse)) return false;
        ClientInfoResponse that = (ClientInfoResponse) o;
        return getClientId() == that.getClientId() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getFirstName(), getLastName(), getPatronymic(), getEmail(), getAddress(), getPhone());
    }
}
