package au.andrew.validator.creditcard.service.impl;

import au.andrew.validator.creditcard.data.model.NumberValidationRequest;
import au.andrew.validator.creditcard.data.model.NumberValidationResponse;
import au.andrew.validator.creditcard.service.NumberValidatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NumberValidatorServiceImpl.class})
public class NumberValidatorServiceImplTest {

    @Autowired
    private NumberValidatorService service;

    @Test
    public void validateCardNumber() {
        NumberValidationRequest request = NumberValidationRequest.builder().cardNumberStr("222").build();
        String response = service.getValidationMessage(request);
        Assert.assertEquals("Unknown: 222 (invalid)", response);

        NumberValidationResponse validationResponse = service.validateCardNumber(request);
        Assert.assertEquals(validationResponse.getValidationMessage(), response);

        request = NumberValidationRequest.builder().cardNumberStr("4111111111111111").build();
        response = service.getValidationMessage(request);
        Assert.assertEquals("VISA: 4111111111111111 (valid)", response);

        validationResponse = service.validateCardNumber(request);
        Assert.assertEquals(validationResponse.getValidationMessage(), response);
    }

    @Test
    public void validateCardNumbers() {
        List<NumberValidationRequest> requests = Stream.of(NumberValidationRequest.builder().cardNumberStr("222").build()).collect(Collectors.toList());
        List<String> response = service.getValidationMessages(requests);
        Assert.assertEquals(1, response.size());
        Assert.assertEquals("Unknown: 222 (invalid)", response.get(0));

        List<NumberValidationResponse> validationResponses = service.validateCardNumber(requests);
        Assert.assertEquals(validationResponses.get(0).getValidationMessage(), response.get(0));

        requests.add(NumberValidationRequest.builder().cardNumberStr("4111111111111111").build());
        response = service.getValidationMessages(requests);
        Assert.assertEquals(2, response.size());
        Assert.assertEquals("Unknown: 222 (invalid)", response.get(0));
        Assert.assertEquals("VISA: 4111111111111111 (valid)", response.get(1));

        validationResponses = service.validateCardNumber(requests);
        Assert.assertEquals(validationResponses.get(0).getValidationMessage(), response.get(0));
        Assert.assertEquals(validationResponses.get(1).getValidationMessage(), response.get(1));
    }


}
