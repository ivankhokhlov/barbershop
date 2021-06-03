package ru.omsu.imit.khokhlov.barbershop.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.configuration.Constraints;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.MaxLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {
    private final Constraints constraints;

    @Autowired
    public MaxLengthValidator(Constraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return field==null||field.length() <= constraints.getMaxNameLength();
    }


}
