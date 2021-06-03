package ru.omsu.imit.khokhlov.barbershop.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.configuration.Constraints;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Duration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DurationValidator implements ConstraintValidator<Duration, Integer> {
    private final Constraints constraints;


    @Autowired
    public DurationValidator(Constraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean isValid(Integer duration, ConstraintValidatorContext constraintValidatorContext) {
        return constraints.getMinDurationReception() <= duration&&duration<=constraints.getMaxDurationReception();
    }


}