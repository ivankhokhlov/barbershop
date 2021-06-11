package ru.omsu.imit.khokhlov.barbershop.dto.request;

import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UpdateMasterRequest {
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
    private String specialization;
    @Password
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String newPassword;

    public UpdateMasterRequest(@MaxLength @NotEmpty @NotNull @NotBlank String firstName,
                               @MaxLength @NotEmpty @NotNull @NotBlank String lastName,
                               @MaxLength String patronymic,
                               @MaxLength @NotEmpty @NotNull @NotBlank String specialization,
                               @Password @MaxLength @NotEmpty @NotNull @NotBlank String newPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.specialization = specialization;
        this.newPassword = newPassword;
    }

    public UpdateMasterRequest() {
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdateMasterRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", specialization='" + specialization + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateMasterRequest that = (UpdateMasterRequest) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(specialization, that.specialization) &&
                Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, specialization, newPassword);
    }
}
