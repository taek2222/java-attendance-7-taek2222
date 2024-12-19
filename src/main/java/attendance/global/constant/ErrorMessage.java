package attendance.global.constant;

public enum ErrorMessage {
    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String get() {
        return PREFIX + message;
    }

    public String get(Object... value) {
        return PREFIX + String.format(message, value);
    }
}