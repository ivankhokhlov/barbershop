package ru.omsu.imit.khokhlov.barbershop.dto.util;

import java.time.LocalDate;
import java.util.Objects;

public class TransferDate {
    private int id;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public TransferDate(int id, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferDate)) return false;
        TransferDate that = (TransferDate) o;
        return getId() == that.getId() &&
                Objects.equals(getDateStart(), that.getDateStart()) &&
                Objects.equals(getDateEnd(), that.getDateEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateStart(), getDateEnd());
    }

    @Override
    public String toString() {
        return "TransferDate{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
