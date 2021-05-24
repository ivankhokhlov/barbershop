package ru.omsu.imit.khokhlov.barbershop.model.user;

public enum UserType {
    ADMIN("Admin"),MASTER("Master"),CLIENT("Client");

    private final String message;

    private UserType(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
