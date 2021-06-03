package ru.omsu.imit.khokhlov.barbershop.dto.request;



import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

public class WeekScheduleRequest {
    @DateTimeFormat(pattern="HH:mm")
    @NotNull
    @NotBlank
    private String timeStart;
    @DateTimeFormat(pattern="HH:mm")
    @NotNull
    @NotBlank
    private String timeEnd;
    private Set<Integer> weekDay;

    public WeekScheduleRequest() {
        super();
    }

    public WeekScheduleRequest(String timeStart, String timeEnd, Set<Integer> weekDays) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.weekDay = weekDays;
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

    public Set<Integer> getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Set<Integer> weekDays) {
        this.weekDay = weekDays;
    }

    @Override
    public String toString() {
        return "WeekScheduleRequest{" +
                "timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", weekDays=" + weekDay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeekScheduleRequest)) return false;
        WeekScheduleRequest that = (WeekScheduleRequest) o;
        return Objects.equals(getTimeStart(), that.getTimeStart()) &&
                Objects.equals(getTimeEnd(), that.getTimeEnd()) &&
                Objects.equals(getWeekDay(), that.getWeekDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeStart(), getTimeEnd(), getWeekDay());
    }
}
