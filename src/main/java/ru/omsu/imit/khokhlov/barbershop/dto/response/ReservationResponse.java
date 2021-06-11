package ru.omsu.imit.khokhlov.barbershop.dto.response;


import java.time.LocalTime;
import java.util.List;

public class ReservationResponse extends ReservationWithoutClientResponse {
    private ClientInfoResponse client;
    private List<ServiceResponse> services;


    public ReservationResponse(LocalTime timeStart, LocalTime timeEnd,
                               ClientInfoResponse client, List<ServiceResponse> services) {
        super(timeStart, timeEnd, true);
        this.client = client;
        this.services = services;
    }


    public ReservationResponse() {
    }

    public ClientInfoResponse getClient() {
        return client;
    }

    public void setClient(ClientInfoResponse client) {
        this.client = client;
    }

    public List<ServiceResponse> getServices() {
        return services;
    }

    public void setServices(List<ServiceResponse> services) {
        this.services = services;
    }

}
