package ru.omsu.imit.khokhlov.barbershop.validator;


import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Login;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class LoginValidator implements ConstraintValidator<Login, String> {
    @Override
    public boolean isValid(String loginField, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^[а-яА-ЯёЁa-zA-Z0-9]+$";
        return loginField.matches(regex);
    }
}