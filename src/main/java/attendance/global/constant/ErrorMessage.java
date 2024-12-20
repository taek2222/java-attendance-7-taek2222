package attendance.global.constant;

public enum ErrorMessage {
    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    NOT_FOUND_NICKNAME("등록되지 않은 닉네임입니다."),
    INVALID_TIME_INPUT("잘못된 형식을 입력하였습니다."),
    CAMPUS_OPERATING_TIME("캠퍼스 운영 시간에만 출석이 가능합니다."),
    NOT_SCHOOL_DAY("%s월 %s일 %s은 등교일이 아닙니다."),
    ALREADY_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");

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