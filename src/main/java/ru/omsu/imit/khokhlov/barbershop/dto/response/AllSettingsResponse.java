package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.util.Objects;

public class AllSettingsResponse extends BaseSettingsResponse {
    private int maxDurationReception;
    private int minDurationReception;

    public AllSettingsResponse() {
    }

    public AllSettingsResponse(int maxNameLength, int minPasswordLength, int maxDurationReception, int minDurationReception) {
        super(maxNameLength, minPasswordLength);
        this.maxDurationReception = maxDurationReception;
        this.minDurationReception = minDurationReception;
    }

    public int getMaxDurationReception() {
        return maxDurationReception;
    }

    public void setMaxDurationReception(int maxDurationReception) {
        this.maxDurationReception = maxDurationReception;
    }

    public int getMinDurationReception() {
        return minDurationReception;
    }

    public void setMinDurationReception(int minDurationReception) {
        this.minDurationReception = minDurationReception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllSettingsResponse)) return false;
        if (!super.equals(o)) return false;
        AllSettingsResponse that = (AllSettingsResponse) o;
        return getMaxDurationReception() == that.getMaxDurationReception() &&
                getMinDurationReception() == that.getMinDurationReception();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMaxDurationReception(), getMinDurationReception());
    }

    @Override
    public String toString() {
        return "AllSettingsResponse{" +
                "maxNameLength=" + getMaxNameLength() +
                ", minPasswordLength=" + getMinPasswordLength() +
                "maxDurationReception=" + maxDurationReception +
                ", minDurationReception=" + minDurationReception +
                '}';
    }
}
