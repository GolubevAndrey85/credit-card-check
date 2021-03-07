package au.andrew.validator.creditcard.data.advice;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CardNumberConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumberValidator {

    String field() default "cardNumberStr";
    String message() default "OK";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
