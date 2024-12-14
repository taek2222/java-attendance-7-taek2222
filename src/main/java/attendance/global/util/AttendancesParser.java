package attendance.global.util;

import attendance.domain.AttendanceType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AttendancesParser {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

    public static void parseAttendances(List<String> attendances) {
        List<String> subList = attendances.subList(1, attendances.size());

        for (String input : subList) {
            String[] nameAndDateTime = input.split(",");

            String nickname = nameAndDateTime[0];
            String dateTime = nameAndDateTime[1];
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime parseDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);

            String attendance = AttendanceType.evaluateAttendance(parseDateTime);
        }
    }
}
