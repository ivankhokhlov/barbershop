package ru.omsu.imit.khokhlov.barbershop.dto.request;

import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
}
