package ru.omsu.imit.khokhlov.barbershop.dto.request;



import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UpdateAdminRequest {
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
    @NotEmpty
    @NotNull
    @NotBlank
    private String oldPassword;
    @Password
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String newPassword;

    public UpdateAdminRequest() {
        super();
    }

    public UpdateAdminRequest(String firstName, String lastName,
                              String patronymic, String position,
                              String oldPassword, String newPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdateAdminRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateAdminRequest that = (UpdateAdminRequest) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(position, that.position) &&
                Objects.equals(oldPassword, that.oldPassword) &&
                Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, position, oldPassword, newPassword);
    }
}
