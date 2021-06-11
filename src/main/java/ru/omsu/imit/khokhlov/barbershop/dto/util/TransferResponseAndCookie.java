package ru.omsu.imit.khokhlov.barbershop.dto.util;

public class TransferResponseAndCookie {
    private Object response;
    private String cookie;

    public TransferResponseAndCookie(Object response, String cookie) {
        this.response = response;
        this.cookie = cookie;
    }

    public TransferResponseAndCookie(String cookie) {
        this.cookie = cookie;
    }

    public TransferResponseAndCookie() {
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

}
