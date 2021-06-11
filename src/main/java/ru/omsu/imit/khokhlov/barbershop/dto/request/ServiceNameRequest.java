package ru.omsu.imit.khokhlov.barbershop.dto.request;

import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ServiceNameRequest {
    @MaxLength
    @NotNull
    @NotBlank
    private String name;

    public ServiceNameRequest(@MaxLength @NotNull String name) {
        this.name = name;
    }

    public ServiceNameRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceNameRequest that = (ServiceNameRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ServiceNameRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
