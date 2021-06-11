package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DayScheduleResponse {
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private List<ReservationWithoutClientResponse> reservations;

    public DayScheduleResponse(LocalDate date, LocalTime timeStart,
                               LocalTime timeEnd, List<ReservationWithoutClientResponse> reservations) {
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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
}
