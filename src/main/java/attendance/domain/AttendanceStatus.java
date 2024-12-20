package attendance.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

public enum AttendanceStatus {
    ATTENDANCE("출석", 5),
    PERCEPTION("지각", 30),
    ABSENCE("결석", Integer.MAX_VALUE)
    ;

    private final String name;
    private final int threshold;

    AttendanceStatus(final String name, final int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static AttendanceStatus evaluateAttendanceStatus(LocalDateTime dateTime) {
        if (dateTime.toLocalTime().equals(LocalTime.MIN)) {
            return ABSENCE;
        }

        int timeBetween = ClassSchedule.calculateTimeBetween(dateTime);
        if (ATTENDANCE.threshold >= timeBetween) {
            return ATTENDANCE;
        }

        if (PERCEPTION.threshold >= timeBetween) {
            return PERCEPTION;
        }
        return ABSENCE;
    }

    public String getName() {
        return name;
    }
}
