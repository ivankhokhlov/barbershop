package ru.omsu.imit.khokhlov.barbershop.dto.request;

import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Login;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RegisterAdminRequest {
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String firstName;
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String lastName;
    @MaxLength
    private String patronymic;
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String position;
    @Login
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String login;
    @Password
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String password;
    public RegisterAdminRequest() {
        super();
    }
    public RegisterAdminRequest(String firstName, String lastName, String patronymic, String position, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.login = login;
        this.password = password;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterAdminRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterAdminRequest)) return false;
        RegisterAdminRequest that = (RegisterAdminRequest) o;
        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getPosition(), that.getPosition()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getPosition(), getLogin(), getPassword());
    }
}
