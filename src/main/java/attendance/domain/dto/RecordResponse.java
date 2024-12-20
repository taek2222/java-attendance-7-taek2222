package attendance.domain.dto;

import java.util.List;

public record RecordResponse(
        List<AttendanceResponse> attendances,
        ResultResponse result
) {
}
