package ru.omsu.imit.khokhlov.barbershop.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrors handleValidation(MethodArgumentNotValidException exc) {
        final ValidationErrors error = new ValidationErrors();
        exc.getBindingResult().getFieldErrors().forEach(fieldError->
            error.getAllErrors().add(new Error(ErrorCodes.VALIDATION_ERROR,fieldError.getField(),fieldError.getDefaultMessage()))
        );
        return error;
    }

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServerErrors handleServerException(ServerException exc) {
        final ServerErrors error = new ServerErrors();
        error.getErrors().add(new Error(exc.getErrorCodes()));
        return error;
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServerErrors handleJsonProcessingException(JsonProcessingException exc) {
        final ServerErrors error = new ServerErrors();
        error.getErrors().add(new Error(ErrorCodes.JSON_IS_WRONG));
        return error;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RuntimeErrors handleRuntimeException(RuntimeException exc) {
        final RuntimeErrors error = new RuntimeErrors();
        if (exc.getMessage().contains("Json")) {
            error.getAllErrors().add("Json parsing error");
            return error;
        }
        error.getAllErrors().add(exc.getMessage());
        return error;
    }

    public static class Error {
        private final ErrorCodes errorCode;
        private final String field;
        private final String message;

        public Error(ErrorCodes errorCode) {
            this.errorCode = errorCode;
            this.field = errorCode.getField();
            this.message = errorCode.getMessage();
        }

        public Error(ErrorCodes errorCode, String field, String message) {
            this.errorCode = errorCode;
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public ErrorCodes getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }
    }
    public static class ServerErrors {
        private List<Error> errors = new ArrayList<>();

        public List<Error> getErrors() {
            return errors;
        }

        public void setErrors(List<Error> allErrors) {
            this.errors = allErrors;
        }
    }

    public static class ValidationErrors {
        private List<Error> allErrors = new ArrayList<>();

        public List<Error> getAllErrors() {
            return allErrors;
        }

        public void setAllErrors(List<Error> allErrors) {
            this.allErrors = allErrors;
        }
    }
    public static class RuntimeErrors {
        private List<String> allErrors = new ArrayList<>();

        public List<String> getAllErrors() {
            return allErrors;
        }

        public void setAllErrors(List<String> allErrors) {
            this.allErrors = allErrors;
        }
    }
}
