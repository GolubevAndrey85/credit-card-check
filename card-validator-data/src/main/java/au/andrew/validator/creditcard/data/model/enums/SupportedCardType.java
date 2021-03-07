package au.andrew.validator.creditcard.data.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter @AllArgsConstructor
public enum SupportedCardType {

    AMEX(Arrays.asList("34", "37"), Collections.singletonList(15), "AMEX"),
    DISCOVER(Collections.singletonList("6011"), Collections.singletonList(16), "Discover"),
    MASTER_CARD(Arrays.asList("51", "52", "53", "54", "55"), Collections.singletonList(16), "MasterCard"),
    VISA(Collections.singletonList("4"), Arrays.asList(13, 16), "VISA"),
    UNKNOWN(Collections.EMPTY_LIST, Collections.EMPTY_LIST, "Unknown");

    private final List<String> start;
    private final List<Integer> length;
    private final String name;

    public static SupportedCardType getTypeByNumber(String number) {
        SupportedCardType supportedCardType = UNKNOWN;
        if(number == null || number.length() == 0) return supportedCardType;
        for (SupportedCardType type : SupportedCardType.values()) {
            Boolean startPresented = type.start.stream().anyMatch(number::startsWith);
            Boolean ifLengthMatches = type.length.stream().anyMatch(l -> number.length() == l);
            if(startPresented && ifLengthMatches) {
                supportedCardType = type;
                break;
            }
        }
        return supportedCardType;
    }

}
