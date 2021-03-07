package au.andrew.validator.creditcard.service.impl;

import au.andrew.validator.creditcard.data.model.NumberValidationRequest;
import au.andrew.validator.creditcard.data.model.NumberValidationResponse;
import au.andrew.validator.creditcard.data.model.enums.SupportedCardType;
import au.andrew.validator.creditcard.service.NumberValidatorService;
import au.andrew.validator.creditcard.service.util.CardNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumberValidatorServiceImpl implements NumberValidatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberValidatorServiceImpl.class);
    public static final String VALID = "valid";
    public static final String INVALID = "invalid";

    @Override
    public String getValidationMessage(NumberValidationRequest request) {
        String result = "%s: %s (%s)";
        String cardNumberStr = CardNumberUtil.sanitise(request.getCardNumberStr());
        SupportedCardType type = SupportedCardType.getTypeByNumber(cardNumberStr);
        String validity = CardNumberUtil.validateNumberByLuhnAlgorithm(cardNumberStr) ? VALID : INVALID;
        return String.format(result, type.getName(), request.getCardNumberStr(), validity);
    }

    @Override
    public List<String> getValidationMessages(List<NumberValidationRequest> requests) {
        return requests.stream().map(this::getValidationMessage).collect(Collectors.toList());
    }

    @Override
    public NumberValidationResponse validateCardNumber(NumberValidationRequest request) {
        return NumberValidationResponse.builder().validationMessage(getValidationMessage(request)).build();
    }

    @Override
    public List<NumberValidationResponse> validateCardNumber(List<NumberValidationRequest> requests) {
        return requests.stream().map(this::validateCardNumber).collect(Collectors.toList());
    }

}
