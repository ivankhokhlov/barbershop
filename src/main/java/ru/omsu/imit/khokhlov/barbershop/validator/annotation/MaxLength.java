package ru.omsu.imit.khokhlov.barbershop.validator.annotation;



import ru.omsu.imit.khokhlov.barbershop.validator.MaxLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MaxLength.List.class)
@Constraint(validatedBy = MaxLengthValidator.class)
public @interface MaxLength {


    String message() default "FIELD_TOO_LONG";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        MaxLength[] value();

    }
}
