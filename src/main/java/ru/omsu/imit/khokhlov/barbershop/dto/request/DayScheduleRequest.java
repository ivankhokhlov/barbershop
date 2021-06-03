package ru.omsu.imit.khokhlov.barbershop.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DayScheduleRequest {

    @NotNull
    private DayOfWeek weekDay;
    @DateTimeFormat(pattern="HH:mm")
    @NotNull
    @NotBlank
    private String timeStart;
    @DateTimeFormat(pattern="HH:mm")
    @NotNull
    @NotBlank
    private String timeEnd;

    public DayScheduleRequest(DayOfWeek weekDay, String timeStart, String timeEnd) {
        this.weekDay = weekDay;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public DayScheduleRequest() {super();
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getTimeStart() {
        return LocalTime.parse(timeStart, DateTimeFormatter.ofPattern("H:mm"));
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return LocalTime.parse(timeEnd, DateTimeFormatter.ofPattern("H:mm"));
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "DayScheduleRequest{" +
                "weekDay=" + weekDay +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayScheduleRequest)) return false;
        DayScheduleRequest that = (DayScheduleRequest) o;
        return weekDay == that.weekDay &&
                Objects.equals(timeStart, that.timeStart) &&
                Objects.equals(timeEnd, that.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekDay, timeStart, timeEnd);
    }
}
