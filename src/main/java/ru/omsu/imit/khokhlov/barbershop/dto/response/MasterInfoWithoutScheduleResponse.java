package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.util.List;


public class MasterInfoWithoutScheduleResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private SpecializationResponse specialization;
    private List<ServiceResponse> services;

    public MasterInfoWithoutScheduleResponse() {
    }

    public MasterInfoWithoutScheduleResponse(Integer id, String firstName, String lastName, String patronymic,
                                             SpecializationResponse specialization, List<ServiceResponse> services) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.specialization = specialization;
        this.services = services;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public SpecializationResponse getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationResponse specialization) {
        this.specialization = specialization;
    }

    public List<ServiceResponse> getServices() {
        return services;
    }

    public void setServices(List<ServiceResponse> services) {
        this.services = services;
    }
}
