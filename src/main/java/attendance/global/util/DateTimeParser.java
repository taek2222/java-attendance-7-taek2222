package attendance.global.util;

import attendance.global.constant.ErrorMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    private static final String TIME_PATTERN = "HH:mm";

    public static LocalDateTime parseDateTime(LocalDate date, String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        LocalTime parseTime = getLocalTime(time, dateTimeFormatter);
        return LocalDateTime.of(date, parseTime);
    }

    private static LocalTime getLocalTime(final String time, final DateTimeFormatter dateTimeFormatter) {
        try {
            return LocalTime.parse(time, dateTimeFormatter);
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.get());
        }
    }


}
