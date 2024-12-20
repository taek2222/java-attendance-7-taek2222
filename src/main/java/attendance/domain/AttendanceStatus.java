package attendance.domain;

import java.time.LocalDateTime;

public enum AttendanceStatus {
    ATTENDANCE("출석", 5),
    PERCEPTION("지각", 30),
    ABSENCE("결석", Integer.MAX_VALUE)
    ;

    private final String status;
    private final int threshold;

    AttendanceStatus(final String status, final int threshold) {
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}
