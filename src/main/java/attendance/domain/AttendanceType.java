package attendance.domain;

import java.time.LocalDateTime;

public enum AttendanceType {
    TARDINESS("지각", 5),
    ABSENCE("결석", 30),
    ATTENDANCE("출석", 0)
    ;

    private final String type;
    private final int threshold;

    AttendanceType(final String type, final int threshold) {
        this.type = type;
        this.threshold = threshold;
    }

    public static String evaluateAttendance (LocalDateTime dateTime) {
        int tardyTime = DayOfTheWeek.calculateTardyTime(dateTime);

        if (ABSENCE.threshold < tardyTime) {
            return ABSENCE.type;
        }

        if (TARDINESS.threshold < tardyTime) {
            return TARDINESS.type;
        }

        return ATTENDANCE.type;
    }
}
