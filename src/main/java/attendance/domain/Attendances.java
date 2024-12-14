package attendance.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Attendances {

    private final List<Attendance> attendances;

    public Attendances(final List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void updateAttendances(Crew crew, LocalDateTime dateTime) {
        Attendance findAttendance = findAttendanceByDate(dateTime);
        findAttendance.updateCrew(crew);
    }

    public void updateNewAttendance(Crew crew, LocalDateTime dateTime) {
        Attendance attendance = findAttendanceByDate(dateTime);
        if (attendance.isSameCrew(crew))
            throw new IllegalArgumentException(); // todo : 이미 출석한 학생
        updateAttendances(crew, dateTime);
    }

    public Attendance findAttendanceByDate(final LocalDateTime dateTime) {
        return attendances.stream()
                .filter(attendance -> attendance.isSameDate(dateTime))
                .findFirst()
                .orElseThrow();
    }
}
