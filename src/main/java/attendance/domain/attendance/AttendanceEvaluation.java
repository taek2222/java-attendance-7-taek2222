package attendance.domain.attendance;

import java.util.Arrays;

public enum AttendanceEvaluation {
    SINCERITY("성실", 0),
    WARNING("경고", 2),
    INTERVIEW("면담", 3),
    EXPULSION("제적", 6)
    ;

    private static final int PERCEPTION_TO_ABSENCE_RATIO = 3;

    private final String name;
    private final int threshold;

    AttendanceEvaluation(final String name, final int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    // 출결 상태 판정
    public static AttendanceEvaluation evaluateAttendance(int perception, int absence) {
        int totalAbsence = absence + (perception / PERCEPTION_TO_ABSENCE_RATIO);
        return Arrays.stream(values())
                .sorted((e1, e2) -> Integer.compare(e2.threshold, e1.threshold)) // 제적 -> .. -> 성실 역순 정렬
                .filter(evaluation -> totalAbsence >= evaluation.threshold) // 평가 기준치 검사
                .findFirst()
                .orElse(SINCERITY);
    }

    public String getName() {
        return name;
    }
}
