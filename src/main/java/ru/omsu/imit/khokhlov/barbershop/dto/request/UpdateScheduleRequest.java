package ru.omsu.imit.khokhlov.barbershop.dto.request;

import org.springframework.format.annotation.DateTimeFormat;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Duration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class UpdateScheduleRequest {
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    @NotBlank
    private String dateStart;
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    @NotBlank
    private String dateEnd;
    private WeekScheduleRequest weekSchedule;
    private List<BoxForDayScheduleRequest> weekDaysSchedule;

    public UpdateScheduleRequest(String dateStart, String dateEnd, WeekScheduleRequest weekSchedule,
                                 List<BoxForDayScheduleRequest> weekDaysSchedule) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.weekSchedule = weekSchedule;
        this.weekDaysSchedule = weekDaysSchedule;
    }

    public UpdateScheduleRequest() {
    }

    public LocalDate getDateStart() {
        return LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public WeekScheduleRequest getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekScheduleRequest weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public List<BoxForDayScheduleRequest> getWeekDaysSchedule() {
        return weekDaysSchedule;
    }

    public void setWeekDaysSchedule(List<BoxForDayScheduleRequest> weekDaysSchedule) {
        this.weekDaysSchedule = weekDaysSchedule;
    }

    @Override
    public String toString() {
        return "UpdateScheduleRequest{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", weekSchedule=" + weekSchedule +
                ", weekDaysSchedule=" + weekDaysSchedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateScheduleRequest that = (UpdateScheduleRequest) o;
        return Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(weekSchedule, that.weekSchedule) &&
                Objects.equals(weekDaysSchedule, that.weekDaysSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd, weekSchedule, weekDaysSchedule);
    }
}
