package attendance.global.constant;

public enum MessageConstant {
    // Output
    OUTPUT_DATE_AND_FUNCTION_SELECTION("오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요."),
    OUTPUT_ATTENDANCE_INFO("%s월 %s일 %s %s (%s)"),

    // Input
    INPUT_FUNCTION_SELECTION("""
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료
                """),

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