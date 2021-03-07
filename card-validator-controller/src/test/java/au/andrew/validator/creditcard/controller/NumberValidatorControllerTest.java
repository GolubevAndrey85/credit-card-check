package au.andrew.validator.creditcard.controller;

import au.andrew.validator.creditcard.data.model.NumberValidationRequest;
import au.andrew.validator.creditcard.data.model.NumberValidationResponse;
import au.andrew.validator.creditcard.service.NumberValidatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NumberValidatorController.class})
public class NumberValidatorControllerTest {

    @Autowired
    private NumberValidatorController controller;

    @MockBean
    private NumberValidatorService service;

    @Test
    public void validateCardNumber() {
        NumberValidationResponse response = NumberValidationResponse.builder().build();
        Mockito.when(service.validateCardNumber(Mockito.any(NumberValidationRequest.class))).thenReturn(response);
        NumberValidationResponse validationResponse = controller.validateCardNumber(NumberValidationRequest.builder().build()).getBody();
        Mockito.verify(service).validateCardNumber(Mockito.any(NumberValidationRequest.class));
        Assert.assertNotNull(validationResponse);
        Assert.assertEquals(response, validationResponse);
    }

    @Test
    public void validateCardNumberWithValid() {
        NumberValidationResponse response = NumberValidationResponse.builder().build();
        Mockito.when(service.validateCardNumber(Mockito.any(NumberValidationRequest.class))).thenReturn(response);
        NumberValidationResponse validationResponse = controller.validateCardNumberWithValid(NumberValidationRequest.builder().build()).getBody();
        Mockito.verify(service).validateCardNumber(Mockito.any(NumberValidationRequest.class));
        Assert.assertNotNull(validationResponse);
        Assert.assertEquals(response, validationResponse);
    }

    @Test
    public void validateCardNumbers() {
        NumberValidationResponse response = NumberValidationResponse.builder().build();
        Mockito.when(service.validateCardNumber(Mockito.anyList())).thenReturn(Collections.singletonList(response));
        List<NumberValidationResponse> validationResponse = controller.validateCardNumbers(Collections.singletonList(NumberValidationRequest.builder().build())).getBody();
        Mockito.verify(service).validateCardNumber(Mockito.anyList());
        Assert.assertNotNull(validationResponse);
        Assert.assertEquals(response, validationResponse.get(0));
    }

}
