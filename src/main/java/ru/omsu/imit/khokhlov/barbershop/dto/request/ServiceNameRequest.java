package ru.omsu.imit.khokhlov.barbershop.dto.request;

import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ServiceNameRequest {
    @MaxLength
    @NotNull
    @NotBlank
    private String name;

    public ServiceNameRequest(@MaxLength @NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
