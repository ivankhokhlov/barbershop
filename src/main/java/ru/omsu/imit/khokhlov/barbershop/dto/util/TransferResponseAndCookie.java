package ru.omsu.imit.khokhlov.barbershop.dto.util;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferResponseAndCookie)) return false;
        TransferResponseAndCookie that = (TransferResponseAndCookie) o;
        return Objects.equals(getResponse(), that.getResponse()) &&
                Objects.equals(getCookie(), that.getCookie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResponse(), getCookie());
    }
}
