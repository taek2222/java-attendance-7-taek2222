package attendance.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Attendance {

    private final LocalDateTime date;
    private final List<Crew> crews;

    public Attendance(final LocalDateTime date, final List<Crew> crews) {
        this.date = date;
        this.crews = crews;
    }

    public boolean isSameDate(LocalDateTime dateTime) {
        return this.date.toLocalDate().isEqual(dateTime.toLocalDate());
    }

    public void updateCrew(Crew newCrew) {
        Crew findCrew = crews.stream()
                .filter(crew -> crew.equals(newCrew))
                .findFirst()
                .orElseThrow();
        findCrew.updateCrew(newCrew);
    }
}
