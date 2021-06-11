package ru.omsu.imit.khokhlov.barbershop.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class ServicesRequest {
    @NotNull
    @NotEmpty
    private List<ServiceRequest> serviceRequests;

    public ServicesRequest(List<ServiceRequest> serviceRequests) {
        this.serviceRequests = serviceRequests;
    }

    public ServicesRequest() {
    }

    public List<ServiceRequest> getServiceRequests() {
        return serviceRequests;
    }

    public void setServiceRequests(List<ServiceRequest> serviceRequests) {
        this.serviceRequests = serviceRequests;
    }

    @Override
    public String toString() {
        return "ServicesRequest{" +
                "serviceRequests=" + serviceRequests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServicesRequest)) return false;
        ServicesRequest that = (ServicesRequest) o;
        return Objects.equals(getServiceRequests(), that.getServiceRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceRequests());
    }
}
