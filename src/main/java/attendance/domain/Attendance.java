package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Attendance {

    private LocalDateTime dateTime;

    public Attendance(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isDefault() {
        return dateTime.toLocalTime().equals(LocalTime.MIN);
    }

    public void updateDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isSameDate(LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }
}
