package ru.omsu.imit.khokhlov.barbershop.validator;

import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.validator.annotation.NotNullInList;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class NotNullInListValidator implements ConstraintValidator<NotNullInList, List<Integer>> {
    @Override
    public boolean isValid(List<Integer> list, ConstraintValidatorContext constraintValidatorContext) {
        for (Integer item:list){
            if(item==null){
                return false;
            }
        }
        return true;

    }


}