package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.time.LocalTime;
import java.util.Objects;

public class ReservationResponse extends ReservationWithoutClientResponse {
    private ClientInfoResponse client;

    public ReservationResponse(LocalTime timeStart, LocalTime timeEnd, ClientInfoResponse client) {
        super(timeStart, timeEnd, true);
        this.client = client;
    }

    public ReservationResponse() {
    }

    public ClientInfoResponse getClient() {
        return client;
    }

    public void setClient(ClientInfoResponse client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationResponse)) return false;
        if (!super.equals(o)) return false;
        ReservationResponse that = (ReservationResponse) o;
        return Objects.equals(getClient(), that.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getClient());
    }
}
