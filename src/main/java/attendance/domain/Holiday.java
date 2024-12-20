package attendance.domain;

import java.time.LocalDateTime;

public enum Holiday {
    SATURDAY(6),
    SUNDAY(7)
    ;

    private static final int CHRISTMAS = 25;

    private final int dayOfWeekValue;

    Holiday(final int dayOfWeekValue) {
        this.dayOfWeekValue = dayOfWeekValue;
    }

    public static boolean isHoliday(LocalDateTime dateTime) {
        return isWeekend(dateTime) || isChristmas(dateTime);
    }

    private static boolean isChristmas(final LocalDateTime dateTime) {
        return dateTime.getDayOfMonth() == CHRISTMAS;
    }

    private static boolean isWeekend(final LocalDateTime dateTime) {
        int dayOfWeekValue = dateTime.getDayOfWeek().getValue();
        return dayOfWeekValue == SATURDAY.dayOfWeekValue || dayOfWeekValue == SUNDAY.dayOfWeekValue;
    }
}
