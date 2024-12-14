package attendance.global.constant;

public enum MessageConstant {
    // Output

    // Input

    NEW_LINE(System.lineSeparator())
    ;

    private final String message;

    MessageConstant(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    public String get(Object... value) {
        return String.format(message, value);
    }
}