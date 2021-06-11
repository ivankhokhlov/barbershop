package ru.omsu.imit.khokhlov.barbershop.dto.request;

import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class ServiceRequest {
    @MaxLength
    @NotNull
    @NotBlank
    private String name;
    @Positive
    @NotNull
    private int price;
    @Positive
    @NotNull
    private int duration;

    public ServiceRequest(String name, int price,int duration) {
        this.name = name;
        this.price = price;
        this.duration=duration;
    }

    public ServiceRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRequest that = (ServiceRequest) o;
        return price == that.price &&
                duration == that.duration &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, duration);
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
