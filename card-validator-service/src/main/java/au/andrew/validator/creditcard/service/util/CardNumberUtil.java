package au.andrew.validator.creditcard.service.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CardNumberUtil {

    public static final Pattern DIGITS_AND_SPACES_ONLY = Pattern.compile("^ *[0-9][0-9 ]*$");

    public static String sanitise(String request) {
        return request.replaceAll("\\s+", "");
    }

    public static Boolean digitsAndSpacesOnly(String request) {
        return DIGITS_AND_SPACES_ONLY.matcher(request).find();
    }

    public static Boolean validateNumberByLuhnAlgorithm(String numberStr) {
        return digitsAndSpacesOnly(numberStr) &&
                getDoubledForOddIndexReversed(getDigits(numberStr)).stream()
                        .map(CardNumberUtil::getSumOfDigits)
                        .mapToInt(Integer::intValue)
                        .sum() % 10 == 0;
    }

    public static List<Integer> getDigits(String numberStr) {
        return numberStr.chars()
                .mapToObj(ch -> (char) ch)
                .map(Character::getNumericValue)
                .collect(Collectors.toList());
    }

    public static List<Integer> getDoubledForOddIndexReversed(List<Integer> singled) {
        List<Integer> doubled = new LinkedList<>();
        for (int i = singled.size() - 1; i >= 0; i--) {
            Integer num = singled.get(i);
            if ((singled.size() - i) % 2 != 0) {
                doubled.add(num);
            } else {
                doubled.add(num * 2);
            }
        }
        return doubled;
    }

    public static Integer getSumOfDigits(Integer number) {
        if (number <= 9) return number;
        return number / 10 + number % 10;
    }


}
