package ru.omsu.imit.khokhlov.barbershop.utils;

public enum ErrorCodes {
    RESERVATION_IS_NOT_POSSIBLE("masterId or specialization", "All the masters are busy or such services are not provided"),
    RESERVATION_NOT_FOUND("receipt", "Reservation with this receipt not found"),
    SPECIALIZATION_NOT_FOUND("specialization", "Specialization not found"),
    JSON_IS_WRONG("", "Json is wrong"),
    MASTER_SPECIALIZATION_NOT_FOUND("specialization", "Masters with this specialization not found\""),
    USER_NOT_FOUND("login", "User not found"),
    RESERVATION_IS_OCCUPIED("timeStart", "Reservation is occupied"),
    SCHEDULE_IS_OCCUPIED("schedule", "Schedule is occupied"),
    MASTER_ID_NOT_FOUND("masterId", "Master with this id not found"),
    USER_ALREADY_REGISTER("login", "User has already registered"),
    WRONG_PASSWORD("password", "Password is wrong"),
    COOKIE_NOT_FOUND("uuid", "Cookie with this UUID not found"),
    INVALID_COOKIE("cookie", "Cookie is wrong"),
    INVALID_JAVA_SESSION_ID("JAVASESSIONID", "JAVASESSIONID is wrong"),
    ACCESS_DENIED("user", "Not enough permissions for this operation"),
    ALL_SCHEDULE_IS_EMPTY("weekSchedule,weekDaysSchedule", "All schedule is empty"),
    INVALID_SCHEDULE("weekSchedule,weekDaysSchedule", "All schedule is full"),
    INVALID_RECORD_TO_THE_MASTER("masterId or specialization", "MasterID and Specialization is wrong"),
    RECORD_TO_THE_MASTER_IS_EMPTY("masterId or specialization", "MasterID and Specialization is empty"),
    INVALID_WEEKDAY("weekDay", "Day of the week meets twice"),
    //NO_SUCH_OPERATION("","Such an operation is not available"),
    INVALID_CLIENT_ID("id", "Client id is wrong"),
    SERVICE_NOT_FOUND("serviceNameRequests", "Service not found"),
    SERVICE_NOT_PROVIDED("serviceNameRequests", "This service is not provided"),
    VALIDATION_ERROR("", "");
    private final String field;
    private final String message;

    ErrorCodes(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }


}