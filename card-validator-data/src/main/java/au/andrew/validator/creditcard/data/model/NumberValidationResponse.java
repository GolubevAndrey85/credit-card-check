package au.andrew.validator.creditcard.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberValidationResponse {
    private String validationMessage;
}
