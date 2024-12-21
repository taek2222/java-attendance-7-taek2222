package attendance.view;

import static attendance.global.constant.ErrorMessage.INVALID_INPUT;

import java.util.List;

public class InputValidator {

    private static final List<String> FUNCTION = List.of("1", "2", "3", "4", "Q");

    public static void validateFunctionSelection(String input) {
        if (!FUNCTION.contains(input)) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    public static void validateIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }
}
