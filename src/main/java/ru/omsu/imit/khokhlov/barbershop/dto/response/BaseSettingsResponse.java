package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.util.Objects;

public class BaseSettingsResponse {
    private int maxNameLength;
    private int minPasswordLength;

    public BaseSettingsResponse() {
    }

    public BaseSettingsResponse(int maxNameLength, int minPasswordLength) {
        this.maxNameLength = maxNameLength;
        this.minPasswordLength = minPasswordLength;
    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    @Override
    public String toString() {
        return "BaseSettingsResponse{" +
                "maxNameLength=" + maxNameLength +
                ", minPasswordLength=" + minPasswordLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseSettingsResponse)) return false;
        BaseSettingsResponse that = (BaseSettingsResponse) o;
        return getMaxNameLength() == that.getMaxNameLength() &&
                getMinPasswordLength() == that.getMinPasswordLength();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaxNameLength(), getMinPasswordLength());
    }
}
