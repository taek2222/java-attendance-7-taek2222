package attendance.global.constant;

public enum MessageConstant {
    // Output
    OUTPUT_DATE_AND_FUNCTION_SELECTION("오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요."),
    OUTPUT_ATTENDANCE_DETAIL("%d월 %02d일 %s %s (%s) "),
    OUTPUT_MODIFIED_RESULT("-> %s (%s) 수정 완료!"),
    OUTPUT_ATTENDANCE_RECORD("이번 달 %s의 출석 기록입니다."),
    OUTPUT_ATTENDANCE_RESULT("""
                출석: %d회
                지각: %d회
                결석: %d회
                """),
    OUTPUT_ATTENDANCE_EVALUATION("%s 대상자입니다."),

    // Input,
    INPUT_TIME_FOR_MODIFICATION("언제로 변경하겠습니까?"),
    INPUT_DAY_FOR_MODIFICATION("수정하려는 날짜(일)를 입력해 주세요."),
    INPUT_NICKNAME_FOR_MODIFICATION("출석을 수정하려는 크루의 닉네임을 입력해 주세요."),
    INPUT_NICKNAME("닉네임을 입력해 주세요."),
    INPUT_SCHOOL_START_TIME("등교 시간을 입력해 주세요."),
    INPUT_FUNCTION_SELECTION("""
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료"""),

    DEFAULT_TIME("--:--"),
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