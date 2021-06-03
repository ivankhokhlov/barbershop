package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.util.Objects;

public class SpecializationResponse {
    private  int id ;
    private String name;

    public SpecializationResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SpecializationResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(o instanceof SpecializationResponse)) return false;
        SpecializationResponse that = (SpecializationResponse) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
