package attendance.domain;

import static attendance.global.constant.ErrorMessage.ALREADY_ATTENDANCE;

import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.ModifiedResponse;
import attendance.domain.dto.RecordResponse;
import attendance.domain.dto.RegisteredResponse;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Crew {

    private final String nickname;
    private final List<Attendance> attendances;
    private Result result;

    public Crew(final String nickname) {
        this.nickname = nickname;
        this.attendances = new ArrayList<>();
        generateDefaultAttendances();
        this.result = new Result(attendances);
    }

    public RegisteredResponse registerAttendance(LocalDateTime dateTime) {
        validationAlreadyAttendance(dateTime.toLocalDate());
        Attendance attendance = new Attendance(dateTime);
        attendances.add(attendance);
        return new RegisteredResponse(
                attendance.createResponse()
        );
    }

    public ModifiedResponse updateAttendance(LocalDateTime dateTime) {
        Attendance oldAttendance = findAttendanceByDate(dateTime.toLocalDate());
        AttendanceResponse oldAttendanceResponse = oldAttendance.createResponse();

        Attendance newAttendance = updateDateTime(dateTime, oldAttendance);
        return new ModifiedResponse(
                oldAttendanceResponse,
                newAttendance.createResponse()
        );
    }

    public RecordResponse createResponse() {
        return new RecordResponse(
                nickname,
                getAttendanceResponses(),
                result.createResponse()
        );
    }

    private List<AttendanceResponse> getAttendanceResponses() {
        return attendances.stream()
                .map(Attendance::createResponse)
                .toList();
    }

    private Attendance updateDateTime(final LocalDateTime dateTime, final Attendance attendance) {
        Attendance updated = attendance.updateDateTime(dateTime);
        updateResult();
        return updated;
    }

    private void updateResult() {
        this.result = new Result(attendances);
    }

    private Attendance findAttendanceByDate(final LocalDate date) {
        return attendances.stream()
                .filter(attendance -> attendance.isSameDate(date))
                .findFirst()
                .orElse(null);
    }

    public boolean isSameNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    private void generateDefaultAttendances() {
        LocalDateTime nowDateTime = DateTimes.now();
        LocalDateTime defaultDateTime = nowDateTime.withDayOfMonth(1).with(LocalTime.MIN);

        while (!defaultDateTime.isAfter(nowDateTime)) {
            if (isHoliday(defaultDateTime.toLocalDate())) {
                defaultDateTime = defaultDateTime.plusDays(1);
                continue;
            }
            attendances.add(new Attendance(defaultDateTime));
            defaultDateTime = defaultDateTime.plusDays(1);
        }
    }

    private boolean isHoliday(final LocalDate defaultDate) {
        return Holiday.isHoliday(defaultDate);
    }

    private void validationAlreadyAttendance(final LocalDate date) {
        if (isAttendanceExist(date)) {
            throw new IllegalArgumentException(ALREADY_ATTENDANCE.get());
        }
    }

    private boolean isAttendanceExist(final LocalDate date) {
        return findAttendanceByDate(date) != null;
    }
}
