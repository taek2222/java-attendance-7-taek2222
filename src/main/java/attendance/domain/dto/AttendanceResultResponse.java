package attendance.domain.dto;

public record AttendanceResultResponse(
        int attendance,
        int perception,
        int absence,
        String weeding
) {
}
