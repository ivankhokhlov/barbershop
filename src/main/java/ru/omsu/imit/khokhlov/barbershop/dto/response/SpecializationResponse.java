package ru.omsu.imit.khokhlov.barbershop.dto.response;

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


}
