package au.andrew.validator.creditcard.controller;

import au.andrew.validator.creditcard.data.model.NumberValidationRequest;
import au.andrew.validator.creditcard.data.model.NumberValidationResponse;
import au.andrew.validator.creditcard.service.NumberValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "card/number/")
public class NumberValidatorController {

    @Autowired
    private NumberValidatorService validatorService;

    /**
     * Returns <code>ResponseEntity<NumberValidationResponse></code> at any case if <code>cardNumberStr</code> is presented
     * If <code>cardNumberStr</code> is not presented returns 400
     *
     * @param request <code>NumberValidationRequest</code>, mapped from <code>cardNumberStr</code> request param
     * @return <code>ResponseEntity<NumberValidationResponse></code>
     */
    @GetMapping("validate")
    public ResponseEntity<NumberValidationResponse> validateCardNumber(@RequestParam(name = "cardNumberStr") NumberValidationRequest request) {
        return new ResponseEntity<>(validatorService.validateCardNumber(request), HttpStatus.OK);
    }

    /**
     * Returns <code>ResponseEntity<NumberValidationResponse></code> when <code>request.cardNumberStr</code> is presented
     * and contains nothing else than digits and spaces, 400 with error message otherwise.
     * Can be used to get detailed explanation why the card number is not valid (now only checks if it is a number)
     * If <code>cardNumberStr</code> is not presented returns 400
     *
     * @param request <code>@Valid NumberValidationRequest</code>, mapped from <code>cardNumberStr</code> request param
     * @return <code>ResponseEntity<NumberValidationResponse></code>
     */
    @PostMapping("validate")
    public ResponseEntity<NumberValidationResponse> validateCardNumberWithValid(@Valid @RequestBody NumberValidationRequest request) {
        return new ResponseEntity<>(validatorService.validateCardNumber(request), HttpStatus.OK);
    }

    /**
     * Checks multiple card numbers
     *
     * @param requests <code>List<NumberValidationRequest></code>
     * @return <code>ResponseEntity<List<NumberValidationResponse>></code>
     */
    @PostMapping("validate/bulk")
    public ResponseEntity<List<NumberValidationResponse>> validateCardNumbers(@RequestBody List<NumberValidationRequest> requests) {
        return new ResponseEntity<>(validatorService.validateCardNumber(requests), HttpStatus.OK);
    }

}
