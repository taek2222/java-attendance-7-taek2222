package attendance.domain.dto;

import java.util.List;

public record AttendancesResponse(
        List<AttendanceResponse> attendanceResponses
) {
}
