package ru.omsu.imit.khokhlov.barbershop.dto.request;


import org.springframework.format.annotation.DateTimeFormat;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class RegisterMasterRequest {
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
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    @NotBlank
    private String dateStart;
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    @NotBlank
    private String dateEnd;
    @MaxLength
    @NotEmpty
    @NotNull
    @NotBlank
    private String specialization;
    @NotEmpty
    @NotNull
    @NotNullInList
    private List<ServiceRequest> serviceRequests;
    private WeekScheduleRequest weekSchedule;
    private List<BoxForDayScheduleRequest> weekDaysSchedule;



    public RegisterMasterRequest(@MaxLength @NotEmpty @NotNull String firstName,
                                 @MaxLength @NotEmpty @NotNull String lastName,
                                 @MaxLength String patronymic,
                                 @Login @MaxLength @NotEmpty @NotNull String login,
                                 @Password @MaxLength @NotEmpty @NotNull String password,
                                 @NotNull String dateStart, @NotNull String dateEnd,
                                 @MaxLength @NotEmpty @NotNull String specialization,
                                 @NotEmpty @NotNull @NotNullInList List<ServiceRequest> serviceRequests,
                                 WeekScheduleRequest weekSchedule,
                                 List<BoxForDayScheduleRequest> weekDaysSchedule) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.specialization = specialization;
        this.serviceRequests = serviceRequests;
        this.weekSchedule = weekSchedule;
        this.weekDaysSchedule = weekDaysSchedule;
    }

    public RegisterMasterRequest() {
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

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<ServiceRequest> getServiceRequests() {
        return serviceRequests;
    }

    public void setServiceRequests(List<ServiceRequest> serviceRequests) {
        this.serviceRequests = serviceRequests;
    }

    public WeekScheduleRequest getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekScheduleRequest weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public List<BoxForDayScheduleRequest> getWeekDaysSchedule() {
        return weekDaysSchedule;
    }

    public void setWeekDaysSchedule(List<BoxForDayScheduleRequest> weekDaysSchedule) {
        this.weekDaysSchedule = weekDaysSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterMasterRequest)) return false;
        RegisterMasterRequest that = (RegisterMasterRequest) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(specialization, that.specialization) &&
                Objects.equals(serviceRequests, that.serviceRequests) &&
                Objects.equals(weekSchedule, that.weekSchedule) &&
                Objects.equals(weekDaysSchedule, that.weekDaysSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, login, password, dateStart, dateEnd, specialization, serviceRequests, weekSchedule, weekDaysSchedule);
    }
}
