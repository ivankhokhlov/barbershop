package ru.omsu.imit.khokhlov.barbershop.utils;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

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
            error.getAllErrors().add(fieldError.getDefaultMessage())
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
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RuntimeErrors handleRuntimeException(RuntimeException exc) {
        final RuntimeErrors error = new RuntimeErrors();
        error.getAllErrors().add(exc.getMessage());
        return error;
    }
    public static class Error{
        private ErrorCodes errorCode;
        private String message;

        public Error(ErrorCodes errorCode) {
            this.errorCode = errorCode;
           this.message= errorCode.getMessage();
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
        private List<String> allErrors = new ArrayList<>();

        public List<String> getAllErrors() {
            return allErrors;
        }

        public void setAllErrors(List<String> allErrors) {
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
