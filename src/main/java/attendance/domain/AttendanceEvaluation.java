package attendance.domain;

public enum AttendanceEvaluation {
    SINCERITY("성실", 0),
    WARNING("경고", 2),
    INTERVIEW("면담", 3),
    WEEDING("제적", 5)
    ;

    private final String name;
    private final int threshold;

    AttendanceEvaluation(final String name, final int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static AttendanceEvaluation evaluateAttendance(int perception, int absence) {
        int totalAbsence = absence + (perception / 3);

        if (totalAbsence > WEEDING.threshold) {
            return WEEDING;
        }

        if (totalAbsence >= INTERVIEW.threshold) {
            return INTERVIEW;
        }

        if (totalAbsence >= WARNING.threshold) {
            return WARNING;
        }

        return SINCERITY;
    }

    public String getName() {
        return name;
    }
}
