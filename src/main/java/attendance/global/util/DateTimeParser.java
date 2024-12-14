package attendance.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    private static final String TIME_PATTERN = "HH:mm";

    public static LocalDateTime parseCrew(LocalDate date, String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        LocalTime parseTime = LocalTime.parse(time, dateTimeFormatter);
        return LocalDateTime.of(date, parseTime);
    }
}
