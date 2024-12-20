package attendance.global.constant;

public enum ErrorMessage {
    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    NOT_FOUND_NICKNAME("등록되지 않은 닉네임입니다."),
    INVALID_TIME_INPUT("잘못된 형식을 입력하였습니다.")
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