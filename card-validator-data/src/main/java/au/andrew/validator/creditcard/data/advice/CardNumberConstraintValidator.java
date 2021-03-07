package au.andrew.validator.creditcard.data.advice;

import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CardNumberConstraintValidator implements ConstraintValidator<CardNumberValidator, String> {

    private static final Pattern DIGITS_AND_SPACES_ONLY = Pattern.compile("^\\d+$");
    private String field;
    private String message;

    @Override
    public void initialize(CardNumberValidator constraintAnnotation) {
        field = constraintAnnotation.field();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (ObjectUtils.isEmpty(s)) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(field + " is empty").addConstraintViolation();
                return false;
            } else if(!DIGITS_AND_SPACES_ONLY.matcher(s).find()){
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(field + " does not only contain numbers: " + s + " " + message).addConstraintViolation();
                return false;
            } else
                return true;
        } catch (Exception e) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid card number").addConstraintViolation();
        }
        return false;
    }
}
