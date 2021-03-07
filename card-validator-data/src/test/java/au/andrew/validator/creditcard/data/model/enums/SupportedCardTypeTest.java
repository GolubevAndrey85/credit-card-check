package au.andrew.validator.creditcard.data.model.enums;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SupportedCardTypeTest {

    @Test
    public void getTypeByNumber() {
        Map<String, String> cardTypes = new HashMap<>();
        cardTypes.put("4111111111111111", "VISA");
        cardTypes.put("4012888888881881", "VISA");
        cardTypes.put("378282246310005", "AMEX");
        cardTypes.put("6011111111111117", "Discover");
        cardTypes.put("5105105105105100", "MasterCard");

        cardTypes.put("4111111111111", "VISA");
        cardTypes.put("5105105105105106", "MasterCard");
        cardTypes.put("9111111111111111", "Unknown");

        cardTypes.put("111111111", "Unknown");
        cardTypes.put("34150014", "Unknown");
        cardTypes.put("", "Unknown");
        cardTypes.put(null, "Unknown");
        cardTypes.put(" any words ", "Unknown");
        cardTypes.put(" &^ $3!! .. ", "Unknown");
        cardTypes.put("0", "Unknown");
        cardTypes.forEach((key, value) -> Assert.assertEquals(value, SupportedCardType.getTypeByNumber(key).getName()));
    }
}
