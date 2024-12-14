package attendance.domain;

import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.CrewResponse;
import java.time.LocalDateTime;
import java.util.List;

public class Attendance {

    private final LocalDateTime date;
    private final List<Crew> crews;

    public Attendance(final LocalDateTime date, final List<Crew> crews) {
        this.date = date;
        this.crews = crews;
    }
    
    public boolean isSameCrew(Crew crew) {
        return findCrewByCrew(crew) == null;
    }

    public boolean isSameDate(LocalDateTime dateTime) {
        return this.date.toLocalDate().isEqual(dateTime.toLocalDate());
    }

    public void updateCrew(Crew newCrew) {
        Crew findCrew = findCrewByCrew(newCrew);
        findCrew.updateCrew(newCrew);
    }

    public AttendanceResponse createResponseByCrew(Crew crew) {
        Crew findCrew = findCrewByCrew(crew);
        return new AttendanceResponse(
                findCrew.createResponse(),
                date
        );
    }

    public Crew findCrewByCrew(final Crew findCrew) {
        return crews.stream()
                .filter(crew -> crew.equals(findCrew))
                .findFirst()
                .orElse(null);
    }
}
