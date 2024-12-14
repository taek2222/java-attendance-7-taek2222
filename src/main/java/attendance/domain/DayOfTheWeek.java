package attendance.domain;

import attendance.global.constant.ErrorMessage;
import java.time.LocalDateTime;
import java.util.Arrays;

public enum DayOfTheWeek {
    월요일(1, 13, 18),
    화요일(2, 10, 18),
    수요일(3, 10, 18),
    목요일(4, 10, 18),
    금요일(5, 10, 18),
    ;

    private final int dateOfWeek;
    private final int schoolStartTime;
    private final int schoolEndTime;

    DayOfTheWeek(final int dateOfWeek, final int schoolStartTime, final int schoolEndTime) {
        this.dateOfWeek = dateOfWeek;
        this.schoolStartTime = schoolStartTime;
        this.schoolEndTime = schoolEndTime;
    }

    public static int calculateTardyTime(LocalDateTime dateTime) {
        DayOfTheWeek dayOfTheWeek = findByDate(dateTime); // 요일 탐색
        int hour = dateTime.getHour();

        if (hour < 8 || hour == 23) {
            throw new IllegalArgumentException(); // todo 등교 이후 시간 에러
        }

        if (hour > dayOfTheWeek.schoolStartTime) {
            return Integer.MAX_VALUE;
        }

        if (hour == dayOfTheWeek.schoolStartTime) {
            return dateTime.getMinute();
        }

        return Integer.MIN_VALUE;
    }

    public static DayOfTheWeek findByDate(LocalDateTime dateTime) {
        validateChristmasDay(dateTime); // 크리스마스 검사
        int value = dateTime.getDayOfWeek().getValue();
        return Arrays.stream(values())
                .filter(dayOfTheWeek -> dayOfTheWeek.dateOfWeek == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_SCHOOL_ATTENDANCE_DATE.get(
                        dateTime.getMonthValue(),
                        dateTime.getDayOfMonth()
                )));
    }

    private static void validateChristmasDay(LocalDateTime dateTime) {
        if (dateTime.getDayOfMonth() == 25) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SCHOOL_ATTENDANCE_DATE.get(
                    dateTime.getMonthValue(),
                    dateTime.getDayOfMonth()
            ));
        }
    }
}