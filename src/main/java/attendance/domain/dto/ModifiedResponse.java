package attendance.domain.dto;

public record ModifiedResponse(
        AttendanceResponse before,
        AttendanceResponse after
) {
}
