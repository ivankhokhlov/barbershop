package ru.omsu.imit.khokhlov.barbershop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Configuration
@Component
public class Constraints {
    @Value("${max_name_length}")
    private int maxNameLength;
    @Value("${min_password_length}")
    private int minPasswordLength;
    @Value("${max_duration_reception}")
    private int maxDurationReception;
    @Value("${min_duration_reception}")
    private int minDurationReception;
    @Autowired
    public Constraints() {
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
    }
