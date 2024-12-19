package attendance.global.constant;

public enum MessageConstant {
    // Output
    OUTPUT_DATE_AND_FUNCTION_SELECTION("오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요."),

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