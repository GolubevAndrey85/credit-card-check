package au.andrew.validator.creditcard.service;

import au.andrew.validator.creditcard.data.model.NumberValidationRequest;
import au.andrew.validator.creditcard.data.model.NumberValidationResponse;

import java.util.List;

public interface NumberValidatorService {
    String getValidationMessage(NumberValidationRequest request);
    List<String> getValidationMessages(List<NumberValidationRequest> requests);
    NumberValidationResponse validateCardNumber(NumberValidationRequest request);
    List<NumberValidationResponse> validateCardNumber(List<NumberValidationRequest> requests);
}
