package ru.omsu.imit.khokhlov.barbershop.dto.request;


import org.springframework.format.annotation.DateTimeFormat;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;


public class RecordToTheMasterRequest {

    @Positive
    private Integer masterId;
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    @NotBlank
    private String date;
    @DateTimeFormat(pattern="HH:mm")
    @NotNull
    @NotBlank
    private String timeStart;
    @MaxLength
    private String specialization;
    @NotEmpty
    @NotNull
    private List<ServiceNameRequest> serviceNameRequests;

    public RecordToTheMasterRequest() {
    }

    public RecordToTheMasterRequest( Integer masterId,
                                    String date,
                                    String timeStart,
                                    String specialization,
                                    List<ServiceNameRequest> serviceNameRequests) {
        this.masterId = masterId;
        this.date = date;
        this.timeStart = timeStart;
        this.specialization = specialization;
        this.serviceNameRequests = serviceNameRequests;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<ServiceNameRequest> getServiceNameRequests() {
        return serviceNameRequests;
    }

    public void setServiceNameRequests(List<ServiceNameRequest> serviceNameRequests) {
        this.serviceNameRequests = serviceNameRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordToTheMasterRequest)) return false;
        RecordToTheMasterRequest that = (RecordToTheMasterRequest) o;
        return Objects.equals(getMasterId(), that.getMasterId()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getTimeStart(), that.getTimeStart()) &&
                Objects.equals(getSpecialization(), that.getSpecialization()) &&
                Objects.equals(getServiceNameRequests(), that.getServiceNameRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMasterId(), getDate(), getTimeStart(), getSpecialization(), getServiceNameRequests());
    }

    @Override
    public String toString() {
        return "RecordToTheMasterRequest{" +
                "masterId=" + masterId +
                ", date='" + date + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", specialization='" + specialization + '\'' +
                ", serviceNameRequests=" + serviceNameRequests +
                '}';
    }
}
