package attendance.global.validation;

public class CommonValidator {

    public static void validateIsNumeric(String input) { //
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
