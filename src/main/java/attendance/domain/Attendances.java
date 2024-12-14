package attendance.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Attendances {

    private final List<Attendance> attendances;

    public Attendances(final List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void updateAttendances(Crew crew, LocalDateTime dateTime) {
        Attendance findAttendance = attendances.stream()
                .filter(attendance -> attendance.isSameDate(dateTime))
                .findFirst()
                .orElseThrow();
        findAttendance.updateCrew(crew);
    }
}
