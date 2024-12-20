package attendance.domain;

import static attendance.domain.AttendanceStatus.ABSENCE;

import attendance.domain.dto.AttendanceResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Attendance {

    private LocalDateTime dateTime;
    private AttendanceStatus status;

    public Attendance(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.status = ABSENCE;
    }

    public Attendance updateDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.status = AttendanceStatus.evaluateAttendanceStatus(dateTime);
        return this;
    }

    public boolean isDefault() {
        return dateTime.toLocalTime().equals(LocalTime.MIN);
    }

    public boolean isSameDate(LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    public AttendanceResponse createResponse() {
        return new AttendanceResponse(
                dateTime,
                status.getStatus()
        );
    }
}
