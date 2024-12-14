package attendance.domain.dto;

import java.time.LocalTime;

public record CrewResponse(
        String nickname,
        String attendance,
        LocalTime time
) {
}
