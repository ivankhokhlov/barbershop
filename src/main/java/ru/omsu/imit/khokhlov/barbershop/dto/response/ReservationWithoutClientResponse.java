package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.time.LocalTime;


public class ReservationWithoutClientResponse {
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private boolean booked=false;

    public ReservationWithoutClientResponse() {
    }

    public ReservationWithoutClientResponse(LocalTime timeStart, LocalTime timeEnd, boolean booked) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.booked = booked;
    }
    public ReservationWithoutClientResponse(LocalTime timeStart, LocalTime timeEnd) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

}
