package au.andrew.validator.creditcard.data.model;

import au.andrew.validator.creditcard.data.advice.CardNumberValidator;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class NumberValidationRequest {
    @CardNumberValidator(field = "cardNumber", message = "")
    private String cardNumberStr;
}
