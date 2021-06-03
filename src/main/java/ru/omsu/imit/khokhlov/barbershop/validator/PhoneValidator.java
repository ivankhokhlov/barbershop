package ru.omsu.imit.khokhlov.barbershop.validator;


import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class PhoneValidator implements ConstraintValidator<Phone, String> {


    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
        String regex1 = "\\+7[0-9]{3}[0-9]{3}-[0-9]{2}-[0-9]{2}";
        String regex2 = "\\+7[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}";
        String regex3 = "8[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}";
        String regex4 = "8[0-9]{3}[0-9]{3}-[0-9]{2}-[0-9]{2}";
        return phoneField.matches(regex1)||phoneField.matches(regex2)
                ||phoneField.matches(regex3)||phoneField.matches(regex4);
    }

}
