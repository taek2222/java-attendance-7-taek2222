package attendance.domain.time;

import java.time.LocalDate;

public enum Holiday {
    SATURDAY(6),
    SUNDAY(7)
    ;

    private static final int CHRISTMAS = 25;

    private final int dayOfWeekValue;

    Holiday(final int dayOfWeekValue) {
        this.dayOfWeekValue = dayOfWeekValue;
    }

    public static boolean isHoliday(final LocalDate date) {
        return isWeekend(date) || isChristmas(date);
    }

    private static boolean isChristmas(final LocalDate date) {
        return date.getDayOfMonth() == CHRISTMAS;
    }

    private static boolean isWeekend(final LocalDate date) {
        int dayOfWeekValue = date.getDayOfWeek().getValue();
        return dayOfWeekValue == SATURDAY.dayOfWeekValue || dayOfWeekValue == SUNDAY.dayOfWeekValue;
    }
}
