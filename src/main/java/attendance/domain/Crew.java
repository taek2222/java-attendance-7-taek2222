package attendance.domain;

import static attendance.global.constant.ErrorMessage.ALREADY_ATTENDANCE;

import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.ModifiedAttendanceResponse;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Crew {

    private final String nickname;
    private final List<Attendance> attendances;

    public Crew(final String nickname) {
        this.nickname = nickname;
        this.attendances = new ArrayList<>();
        generateDefaultAttendances();
    }

    public Attendance registerAttendance(LocalDateTime dateTime) {
        Attendance attendance = findAttendanceByDate(dateTime.toLocalDate());
        validationAlreadyAttendance(attendance);
        attendance.updateDateTime(dateTime);

        return attendance;
    }

    public ModifiedAttendanceResponse updateAttendance(LocalDateTime dateTime) {
        Attendance oldAttendance = findAttendanceByDate(dateTime.toLocalDate());
        AttendanceResponse oldAttendanceResponse = oldAttendance.createResponse();

        Attendance newAttendance = oldAttendance.updateDateTime(dateTime);
        return new ModifiedAttendanceResponse(
                oldAttendanceResponse,
                newAttendance.createResponse()
        );
    }

    private Attendance findAttendanceByDate(final LocalDate date) {
        return attendances.stream()
                .filter(attendance -> attendance.isSameDate(date))
                .findFirst()
                .orElseThrow();
    }

    public boolean isSameNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    private void generateDefaultAttendances() {
        LocalDateTime nowDateTime = DateTimes.now();
        LocalDateTime defaultDateTime = nowDateTime.withDayOfMonth(1).with(LocalTime.MIN);

        while (!defaultDateTime.isAfter(nowDateTime)) {
            if (isHoliday(defaultDateTime)) {
                defaultDateTime = defaultDateTime.plusDays(1);
                continue;
            }
            attendances.add(new Attendance(defaultDateTime));
            defaultDateTime = defaultDateTime.plusDays(1);
        }
    }

    private boolean isHoliday(final LocalDateTime defaultDateTime) {
        return Holiday.isHoliday(defaultDateTime);
    }

    private void validationAlreadyAttendance(final Attendance attendance) {
        if (!attendance.isDefault()) {
            throw new IllegalArgumentException(ALREADY_ATTENDANCE.get());
        }
    }
}
