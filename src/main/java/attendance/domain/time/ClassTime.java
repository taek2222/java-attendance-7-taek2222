package attendance.domain.time;

import static attendance.global.constant.ErrorMessage.AFTER_CLASS_TIME;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public enum ClassTime {
    MONDAY(List.of(1), LocalTime.of(13, 0), LocalTime.of(18, 0)),
    WEEKDAYS(List.of(2, 3, 4, 5), LocalTime.of(10, 0), LocalTime.of(18, 0))
    ;

    private final List<Integer> dayOfWeekValues;
    private final LocalTime startTime;
    private final LocalTime endTime;

    ClassTime(final List<Integer> dayOfWeekValues, final LocalTime startTime, final LocalTime endTime) {
        this.dayOfWeekValues = dayOfWeekValues;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static int calculateTimeBetween(LocalDateTime dateTime) {
        ClassTime classTime = findTypeByDayOfWeek(dateTime.getDayOfWeek());
        validationAfterClass(dateTime.toLocalTime(), classTime);

        Duration between = Duration.between(classTime.startTime, dateTime.toLocalTime());
        return (int) between.toMinutes();
    }

    private static void validationAfterClass(final LocalTime time, final ClassTime schedule) {
        if (time.isAfter(schedule.endTime)) {
            throw new IllegalArgumentException(AFTER_CLASS_TIME.get());
        }
    }

    private static ClassTime findTypeByDayOfWeek(final DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(type -> type.dayOfWeekValues.contains(dayOfWeek.getValue()))
                .findFirst()
                .orElseThrow();
    }
}
