package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DayScheduleResponse {
    private LocalDate date;
    private List<ReservationWithoutClientResponse> reservations;

    public DayScheduleResponse(LocalDate date, List<ReservationWithoutClientResponse> reservations) {
        this.date = date;
        this.reservations = reservations;
    }

    public DayScheduleResponse() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ReservationWithoutClientResponse> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationWithoutClientResponse> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayScheduleResponse)) return false;
        DayScheduleResponse that = (DayScheduleResponse) o;
        return Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getReservations(), that.getReservations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getReservations());
    }
}
