package attendance.global.util;

import static attendance.global.validation.TimeValidator.validateCampusOperateTime;
import static attendance.global.validation.TimeValidator.validateTimeParse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeParser {

    public static LocalDateTime parseDateTime(final LocalDate date, final String inputTime) {
        LocalTime time = parseTime(inputTime);
        return LocalDateTime.of(date, time);
    }

    public static LocalTime parseTime(final String input) {
        validateTimeParse(input);
        LocalTime time = LocalTime.parse(input);
        validateCampusOperateTime(time);
        return time;
    }
}
