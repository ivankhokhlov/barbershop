package ru.omsu.imit.khokhlov.barbershop.validator.annotation;




import ru.omsu.imit.khokhlov.barbershop.validator.NotNullInListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NotNullInList.List.class)
@Constraint(validatedBy = NotNullInListValidator.class)
public @interface NotNullInList {
    String message() default "INVALID_LOGIN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        NotNullInList[] value();
    }
}

