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

    public AttendanceResults(final List<Attendance> attendances) {
        List<Attendance> subbed = attendances.subList(0, attendances.size() - 1); // 오늘 날짜 제외
        this.attendance = countByStatus(subbed, ATTENDANCE);
        this.perception = countByStatus(subbed, PERCEPTION);
        this.absence = countByStatus(subbed, ABSENCE);
    }

    public AttendanceResultResponse createResponse() {
        return new AttendanceResultResponse(
                attendance,
                perception,
                absence,
                evaluateAttendance()
        );
    }

    private String evaluateAttendance() {
        return AttendanceEvaluation.evaluateAttendance(perception, absence).getName();
    }

    private int countByStatus(final List<Attendance> attendances, final AttendanceStatus status) {
        return (int) attendances.stream()
                .filter(attendance -> attendance.isSameStatus(status))
                .count();
    }
}
