package ru.omsu.imit.khokhlov.barbershop.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.configuration.Constraints;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {
    private final Constraints constraints;


    @Autowired
    public PasswordValidator(Constraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        return constraints.getMinPasswordLength() <= passwordField.length();
    }
}
