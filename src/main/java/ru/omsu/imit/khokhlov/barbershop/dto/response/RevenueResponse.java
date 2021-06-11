package ru.omsu.imit.khokhlov.barbershop.dto.response;


public class RevenueResponse {
    private Integer revenue;

    public RevenueResponse(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }
}
