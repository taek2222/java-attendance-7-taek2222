package attendance.domain.dto;

public record ModifiedAttendanceResponse(
        AttendanceResponse oldAttendance,
        AttendanceResponse newAttendance
) {
}
