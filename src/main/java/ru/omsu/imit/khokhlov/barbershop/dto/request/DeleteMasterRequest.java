package ru.omsu.imit.khokhlov.barbershop.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeleteMasterRequest {
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    @NotBlank
    private String date;

    public DeleteMasterRequest() {super();
    }
    public DeleteMasterRequest(String date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DeleteMasterRequest{" +
                "date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteMasterRequest that = (DeleteMasterRequest) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
