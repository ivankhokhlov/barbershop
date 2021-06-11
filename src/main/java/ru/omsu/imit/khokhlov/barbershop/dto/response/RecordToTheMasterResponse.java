package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class RecordToTheMasterResponse {



    private Integer masterId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String specialization;
    private List<ServiceResponse> serviceResponses;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private int cost;
    private String receipt;

    public RecordToTheMasterResponse(Integer masterId, String firstName, String lastName, String patronymic, String specialization,
                                     List<ServiceResponse> serviceResponses,
                                     LocalDate date, LocalTime timeStart, LocalTime timeEnd,
                                     int cost, String receipt) {
        this.masterId = masterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.specialization = specialization;
        this.serviceResponses = serviceResponses;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.cost = cost;
        this.receipt = receipt;
    }


    public RecordToTheMasterResponse() {
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
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

    public List<ServiceResponse> getServiceResponses() {
        return serviceResponses;
    }

    public void setServiceResponses(List<ServiceResponse> serviceResponses) {
        this.serviceResponses = serviceResponses;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
