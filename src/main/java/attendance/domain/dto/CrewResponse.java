package attendance.domain.dto;

import java.util.List;

public record CrewResponse(
        String nickname,
        List<AttendanceResponse> attendances,
        AttendanceResultResponse attendanceResult
) {
}
