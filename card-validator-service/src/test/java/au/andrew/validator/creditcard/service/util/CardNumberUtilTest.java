package au.andrew.validator.creditcard.service.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardNumberUtilTest {

    @Test
    public void getDigits() {
        String number = "0129";
        List<Integer> result = CardNumberUtil.getDigits(number);
        Assert.assertNotNull(result);
        List<Integer> expected = Stream.of(0, 1, 2, 9).collect(Collectors.toList());
        result.forEach(r -> Assert.assertEquals(expected.get(result.indexOf(r)), r));
    }

    @Test
    public void getDoubledForOddIndexReversed() {
        List<Integer> singled = Stream.of(0, 2, 5, 3, 9).collect(Collectors.toList());
        List<Integer> expected = Stream.of(9, 3 * 2, 5, 2 * 2, 0).collect(Collectors.toList());
        List<Integer> result = CardNumberUtil.getDoubledForOddIndexReversed(singled);
        result.forEach(r -> Assert.assertEquals(expected.get(result.indexOf(r)), r));

        List<Integer> singled_ones = Stream.of(4, 1, 1, 1, 1).collect(Collectors.toList());
        List<Integer> expected_ones = Stream.of(1, 2, 1, 2, 4).collect(Collectors.toList());
        List<Integer> result_ones = CardNumberUtil.getDoubledForOddIndexReversed(singled_ones);
        for (int i = 0; i <= singled_ones.size() - 1; i++) {
            Assert.assertEquals(expected_ones.get(i), result_ones.get(i));
        }
    }

    @Test
    public void  getSumOfDigits() {
        Integer number = 0;
        Assert.assertEquals(number, CardNumberUtil.getSumOfDigits(number));
        number = 1;
        Assert.assertEquals(number, CardNumberUtil.getSumOfDigits(number));
        number = 4;
        Assert.assertEquals(number, CardNumberUtil.getSumOfDigits(number));
        number = 12;
        Assert.assertEquals(Integer.valueOf(3), CardNumberUtil.getSumOfDigits(number));
        number = 18;
        Assert.assertEquals(Integer.valueOf(9), CardNumberUtil.getSumOfDigits(number));
    }

    @Test
    public void validateNumberByLuhnAlgorithm() {
        List<String> testValidValues = Stream.of("4111111111111111", "4012888888881881", "378282246310005",
                "6011111111111117", "5105105105105100").collect(Collectors.toList());
        testValidValues.forEach(v -> Assert.assertTrue(CardNumberUtil.validateNumberByLuhnAlgorithm(v)));
        List<String> testInValidValues = Stream.of("4111111111111", "5105105105105106", "5105 1051 0510 5106",
                "9111111111111111", "--911111tr1111111111", "", "5105 1051 0510 5100").collect(Collectors.toList());
        testInValidValues.forEach(v -> Assert.assertFalse(CardNumberUtil.validateNumberByLuhnAlgorithm(v)));
    }
}
