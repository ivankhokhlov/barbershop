package ru.omsu.imit.khokhlov.barbershop.dto.request;



import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Login;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class LoginRequest {
    @Login
    @MaxLength
    @NotNull
    @NotBlank
    private String login;
    @Password
    @MaxLength
    @NotNull
    @NotBlank
    private String password;

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginRequest() {super();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
