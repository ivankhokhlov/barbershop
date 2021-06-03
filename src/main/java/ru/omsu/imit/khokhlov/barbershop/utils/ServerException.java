package ru.omsu.imit.khokhlov.barbershop.utils;


public class ServerException extends RuntimeException {
    private ErrorCodes errorCodes;
    private String param;
    public ServerException() {
        super();
    }

    public ServerException(ErrorCodes errorCodes, String param) {
        super(String.format(errorCodes.getMessage(), param));
        this.param= param;
        this.errorCodes=errorCodes;
    }

    public ServerException(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.errorCodes=errorCodes;
    }

    public ServerException(ErrorCodes errorCodes, Throwable cause) {
        super(errorCodes.getMessage(), cause);
        this.errorCodes=errorCodes;
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public String getParam() {
        return param;
    }
}
