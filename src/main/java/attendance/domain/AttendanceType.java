package attendance.domain;

import java.time.LocalDateTime;

public enum AttendanceType {
    TARDINESS("(지각)", 5),
    ABSENCE("(결석)", 30),
    ATTENDANCE("(출석)", 0)
    ;

    private final String type;
    private final int threshold;

    AttendanceType(final String type, final int threshold) {
        this.type = type;
        this.threshold = threshold;
    }

    public static AttendanceType evaluateAttendance (LocalDateTime dateTime) {
        int tardyTime = DayOfTheWeek.calculateTardyTime(dateTime);

        if (ABSENCE.threshold < tardyTime) {
            return ABSENCE;
        }

        if (TARDINESS.threshold < tardyTime) {
            return TARDINESS;
        }

        return ATTENDANCE;
    }

    public String getType() {
        return type;
    }
}
