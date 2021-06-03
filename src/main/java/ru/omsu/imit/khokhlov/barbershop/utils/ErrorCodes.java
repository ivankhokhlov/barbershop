package ru.omsu.imit.khokhlov.barbershop.utils;

public enum ErrorCodes {
    RESERVATION_IS_NOT_POSSIBLE( "All the masters are busy or such services are not provided"),
    RESERVATION_NOT_FOUND( "Reservation not found"),
    SPECIALIZATION_NOT_FOUND( "Specialization not found"),
    CLIENT_NOT_FOUND("Client not found"),
    MASTER_SPECIALIZATION_NOT_FOUND( "Master with this id not found\""),
    USER_NOT_FOUND( "User not found"),
    RESERVATION_IS_OCCUPIED( "Reservation is occupied"),
    SCHEDULE_IS_OCCUPIED( "Schedule is occupied"),
    MASTER_ID_NOT_FOUND( "Master with this id not found"),
    USER_ALREADY_REGISTER( "User has already registered"),
    WRONG_PASSWORD( "Password is wrong"),
    COOKIE_NOT_FOUND( "Cookie with this UUID not found"),
    INVALID_COOKIE( "Cookie is wrong"),
    INVALID_JAVA_SESSION_ID( "JAVASESSIONID is wrong"),
    ACCESS_DENIED("Not enough permissions for this operation"),
    USER_ALREADY_LOGIN("User has already login"),
    ALL_SCHEDULE_IS_EMPTY( "All schedule is empty"),
    INVALID_SCHEDULE( "All schedule is full"),
    INVALID_RECORD_TO_THE_MASTER( "MasterID and Specialization is wrong"),
    RECORD_TO_THE_MASTER_IS_EMPTY( "MasterID and Specialization is empty"),
    INVALID_WEEKDAY( "Day of the week meets twice"),
    NO_SUCH_OPERATION("Such an operation is not available"),
    INVALID_CLIENT_ID("Client id is wrong"),
    INVALID_PASSWORD("Password is wrong"),
    SERVICE_NOT_FOUND("Service not found"),
    SERVICE_NOT_PROVIDED("This service is not provided")
    ;

    private final String message;

    private ErrorCodes(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


}