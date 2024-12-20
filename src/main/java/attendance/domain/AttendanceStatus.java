package attendance.domain;

import java.time.LocalDateTime;

public enum AttendanceStatus {
    ATTENDANCE(5),
    PERCEPTION(30),
    ABSENCE(Integer.MAX_VALUE)
    ;

    private final int threshold;

    AttendanceStatus(final int threshold) {
        this.threshold = threshold;
    }

    public static AttendanceStatus evaluateAttendanceStatus(LocalDateTime dateTime) {
        int timeBetween = ClassSchedule.calculateTimeBetween(dateTime);
        if (ATTENDANCE.threshold >= timeBetween) {
            return ATTENDANCE;
        }

        if (PERCEPTION.threshold >= timeBetween) {
            return PERCEPTION;
        }
        return ABSENCE;
    }
}
