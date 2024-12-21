package attendance.domain.dto;

import java.util.List;

public record RecordResponse(
        String nickname,
        List<AttendanceResponse> attendances,
        AttendanceResultResponse attendanceResult
) {
}
