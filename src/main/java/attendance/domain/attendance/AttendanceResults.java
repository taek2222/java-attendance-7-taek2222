package attendance.domain.attendance;

import static attendance.domain.attendance.AttendanceStatus.ABSENCE;
import static attendance.domain.attendance.AttendanceStatus.ATTENDANCE;
import static attendance.domain.attendance.AttendanceStatus.PERCEPTION;

import attendance.domain.dto.AttendanceResultResponse;
import java.util.List;

public class AttendanceResults {

    private final int attendance;
    private final int perception;
    private final int absence;

    public AttendanceResults(List<Attendance> attendances) {
        this.attendance = countByStatus(attendances, ATTENDANCE);
        this.perception = countByStatus(attendances, PERCEPTION);
        this.absence = countByStatus(attendances, ABSENCE);
    }

    public AttendanceResultResponse createResponse() {
        return new AttendanceResultResponse(
                attendance,
                perception,
                absence,
                AttendanceEvaluation.evaluateAttendance(perception, absence).getName()
        );
    }

    private int countByStatus(List<Attendance> attendances, AttendanceStatus status) {
        return (int) attendances.stream()
                .filter(attendance -> attendance.isSameStatus(status))
                .count();
    }
}
