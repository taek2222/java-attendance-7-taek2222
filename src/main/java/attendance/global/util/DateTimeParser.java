package attendance.global.util;

import static attendance.global.constant.ErrorMessage.CAMPUS_OPERATING_TIME;
import static attendance.global.constant.ErrorMessage.INVALID_TIME_INPUT;

import attendance.domain.CampusTime;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeParser {

    public static LocalDateTime parseDateTime(final LocalDate date, final String inputTime) {
        LocalTime time = parseTime(inputTime);
        return LocalDateTime.of(date, time);
    }

    public static LocalTime parseTime(String input) {
        validateTimeParse(input);

        LocalTime time = LocalTime.parse(input);
        validateCampusOperateTime(time);
        return time;
    }

    private static void validateTimeParse(final String time) {
        try {
            LocalTime.parse(time);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_TIME_INPUT.get());
        }
    }

    private static void validateCampusOperateTime(final LocalTime time) {
        if (!CampusTime.isCampusOperateTime(time)){
            throw new IllegalArgumentException(CAMPUS_OPERATING_TIME.get());
        }
    }
}
