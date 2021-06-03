package ru.omsu.imit.khokhlov.barbershop.dto.request;


import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Login;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Password;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Phone;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RegisterClientRequest {
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
    @Email
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String email;
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String address;
    @Phone
    @NotEmpty
    @NotNull
    @NotBlank
    private String phone;
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
    public RegisterClientRequest() {
    super();
    }

    public RegisterClientRequest(String firstName, String lastName,
                                 String patronymic, String email,
                                 String address, String phone,
                                 String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.address = address;
        this.phone = phone;
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
        return "RegisterClientRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterClientRequest)) return false;
        RegisterClientRequest that = (RegisterClientRequest) o;
        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getEmail(), getAddress(), getPhone(), getLogin(), getPassword());
    }
}
