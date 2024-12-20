package attendance.domain;

import static attendance.global.constant.ErrorMessage.AFTER_CLASS_TIME;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public enum AttendanceType {
    MONDAY(List.of(1), LocalTime.of(13, 0), LocalTime.of(18, 0)),
    WEEKDAYS(List.of(2, 3, 4, 5), LocalTime.of(10, 0), LocalTime.of(18, 0))
    ;

    private final List<Integer> dayOfWeekValues;
    private final LocalTime startTime;
    private final LocalTime endTime;

    AttendanceType(final List<Integer> dayOfWeekValues, final LocalTime startTime, final LocalTime endTime) {
        this.dayOfWeekValues = dayOfWeekValues;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static int calculateTimeBetween(LocalDateTime dateTime) {
        AttendanceType attendanceType = findTypeByDayOfWeek(dateTime.getDayOfWeek());
        validationAfterClass(dateTime.toLocalTime(), attendanceType);

        Duration between = Duration.between(attendanceType.startTime, dateTime.toLocalTime());
        return (int) between.toMinutes();
    }

    private static void validationAfterClass(final LocalTime time, final AttendanceType attendanceType) {
        if (time.isAfter(attendanceType.endTime)) {
            throw new IllegalArgumentException(AFTER_CLASS_TIME.get());
        }
    }

    private static AttendanceType findTypeByDayOfWeek(final DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(type -> type.dayOfWeekValues.contains(dayOfWeek.getValue()))
                .findFirst()
                .orElseThrow();
    }
}
