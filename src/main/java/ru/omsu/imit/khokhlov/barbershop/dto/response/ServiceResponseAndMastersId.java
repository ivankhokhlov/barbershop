package ru.omsu.imit.khokhlov.barbershop.dto.response;


import java.util.List;

public class ServiceResponseAndMastersId {
    private ServiceResponse serviceResponse;
    private List<Integer> mastersIds;

    public ServiceResponseAndMastersId(ServiceResponse serviceResponse, List<Integer> mastersIds) {
        this.serviceResponse = serviceResponse;
        this.mastersIds = mastersIds;
    }

    public ServiceResponseAndMastersId() {
    }

    public ServiceResponse getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(ServiceResponse serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public List<Integer> getMastersIds() {
        return mastersIds;
    }

    public void setMastersIds(List<Integer> mastersIds) {
        this.mastersIds = mastersIds;
    }
}
