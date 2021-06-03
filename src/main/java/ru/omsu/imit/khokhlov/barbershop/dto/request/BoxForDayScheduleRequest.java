package ru.omsu.imit.khokhlov.barbershop.dto.request;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BoxForDayScheduleRequest {
    @NotNull
    private DayScheduleRequest daySchedule;

    public BoxForDayScheduleRequest() {
    }

    public BoxForDayScheduleRequest(DayScheduleRequest daySchedule) {
        this.daySchedule = daySchedule;
    }

    public DayScheduleRequest getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(DayScheduleRequest daySchedule) {
        this.daySchedule = daySchedule;
    }

    @Override
    public String toString() {
        return "BoxForDayScheduleRequest{" +
                "daySchedule=" + daySchedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoxForDayScheduleRequest)) return false;
        BoxForDayScheduleRequest that = (BoxForDayScheduleRequest) o;
        return Objects.equals(getDaySchedule(), that.getDaySchedule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDaySchedule());
    }
}
