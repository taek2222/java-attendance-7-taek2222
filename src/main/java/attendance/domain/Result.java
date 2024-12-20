package attendance.domain;

import static attendance.domain.AttendanceStatus.ABSENCE;
import static attendance.domain.AttendanceStatus.ATTENDANCE;
import static attendance.domain.AttendanceStatus.PERCEPTION;

import attendance.domain.dto.ResultResponse;
import java.util.List;

public class Result {

    private final int attendance;
    private final int perception;
    private final int absence;

    public Result(List<Attendance> attendances) {
        this.attendance = countByStatus(attendances, ATTENDANCE);
        this.perception = countByStatus(attendances, PERCEPTION);
        this.absence = countByStatus(attendances, ABSENCE);
    }

    public ResultResponse createResponse() {
        return new ResultResponse(
                attendance,
                perception,
                absence,
                Weeding.evaluateWeeding(perception, absence).getName()
        );
    }

    private int countByStatus(List<Attendance> attendances, AttendanceStatus status) {
        return (int) attendances.stream()
                .filter(attendance -> attendance.isSameStatus(status))
                .count();
    }
}
