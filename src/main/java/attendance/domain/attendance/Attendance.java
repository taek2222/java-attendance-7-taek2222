package attendance.domain.attendance;

import static attendance.domain.attendance.AttendanceStatus.ABSENCE;
import static attendance.domain.attendance.AttendanceStatus.evaluateAttendanceStatus;

import attendance.domain.dto.AttendanceResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Attendance {

    private static final AttendanceStatus DEFAULT_STATUS = ABSENCE;

    private LocalDateTime dateTime;
    private AttendanceStatus status;

    public Attendance(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.status = DEFAULT_STATUS;
    }

    public Attendance updateDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.status = evaluateAttendanceStatus(dateTime);
        return this; // 업데이트 이후 객체 반환
    }

    // 초기 생성 과정에 시간은 LocalTime.MIN 등록
    public boolean isInitialTime() {
        return dateTime.toLocalTime().equals(LocalTime.MIN);
    }

    public boolean isSameDate(LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    public boolean isSameStatus(AttendanceStatus status) {
        return this.status.equals(status);
    }

    public AttendanceResponse createResponse() {
        return new AttendanceResponse(
                dateTime,
                status.getName()
        );
    }
}
