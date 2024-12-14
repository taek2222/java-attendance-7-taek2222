package attendance.global.util;

import attendance.domain.AttendanceType;
import attendance.domain.Crew;
import attendance.domain.CrewNickname;
import java.time.LocalDateTime;

public class CrewParser {

    public static Crew parseCrew(String nickname, LocalDateTime dateTime) {
        AttendanceType attendance = AttendanceType.evaluateAttendance(dateTime);
        String name = CrewNickname.findByName(nickname);
        return new Crew(name, attendance, dateTime.toLocalTime());
    }
}
