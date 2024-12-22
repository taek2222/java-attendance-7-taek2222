package attendance.domain.time;

import java.time.LocalTime;

public enum CampusTime {
    OPEN(LocalTime.of(8, 0, 0)),
    CLOSE(LocalTime.of(23, 0, 0))
    ;

    private final LocalTime time;

    CampusTime(final LocalTime time) {
        this.time = time;
    }

    public static boolean isCampusOperateTime(final LocalTime time) {
        return !OPEN.time.isAfter(time) && !CLOSE.time.isBefore(time);
    }
}
