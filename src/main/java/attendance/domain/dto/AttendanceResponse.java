package attendance.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AttendanceResponse (
        CrewResponse crewResponses,
        LocalDateTime dateTime
) {
}
