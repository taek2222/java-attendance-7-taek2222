package attendance.global.util;

import attendance.domain.AttendanceType;
import attendance.domain.Attendances;
import attendance.domain.Crew;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AttendancesParser {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

    public static void parseAndUpdateAttendances(Attendances attendances, List<String> FileAttendances) {
        List<String> subList = FileAttendances.subList(1, FileAttendances.size()); // 첫 리스트 삭제

        for (String input : subList) {
            String[] nameAndDateTime = input.split(",");

            String nickname = nameAndDateTime[0];
            String dateTime = nameAndDateTime[1];
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime parseDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
            AttendanceType attendance = AttendanceType.evaluateAttendance(parseDateTime);

            Crew crew = new Crew(nickname, attendance, parseDateTime.toLocalTime());
            attendances.updateAttendances(crew, parseDateTime);
        }
    }
}
