package attendance.domain.attendance;

import attendance.domain.time.ClassTime;
import java.time.LocalDateTime;
import java.util.Arrays;

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
        int delayMinutes = ClassTime.calculateDelayMinutes(dateTime);
        return Arrays.stream(values())
                .filter(status -> delayMinutes <= status.threshold) // 시간 차이의 일치하는지 조건문
                .findFirst()
                .orElse(ABSENCE);
    }

    public String getName() {
        return name;
    }
}
