package attendance.global.constant;

public enum ErrorMessage {

    INVALID_NAME("등록되지 않은 닉네임입니다."),
    INVALID_FORMAT("잘못된 형식을 입력하였습니다."),
    NOT_SCHOOL_ATTENDANCE_DATE("%s월 %s일 토요일은 등교일이 아닙니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private static final String RETRY = " 다시 입력해 주세요.";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String get() {
        return PREFIX + message + RETRY;
    }

    public String get(Object... value) {
        return PREFIX + String.format(message, value) + RETRY;
    }
}