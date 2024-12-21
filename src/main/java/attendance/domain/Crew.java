package attendance.domain;

import static attendance.global.constant.ErrorMessage.ALREADY_ATTENDANCE;

import attendance.domain.attendance.AttendanceResults;
import attendance.domain.attendance.Attendance;
import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.ModifiedResponse;
import attendance.domain.dto.RecordResponse;
import attendance.domain.dto.RegisteredResponse;
import attendance.domain.time.Holiday;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Crew {

    private final String nickname;
    private final List<Attendance> attendances;
    private AttendanceResults result;

    public Crew(final String nickname) {
        this.nickname = nickname;
        this.attendances = new ArrayList<>();
        generateDefaultAttendances();
        this.result = new AttendanceResults(attendances);
    }

    public RegisteredResponse registerAttendance(LocalDateTime dateTime) {
        Attendance attendance = findAttendanceByDate(dateTime.toLocalDate());
        validationAlreadyAttendance(attendance);
        updateDateTime(dateTime, attendance);
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
        List<Attendance> subbed = attendances.subList(0, attendances.size() - 1);
        return subbed.stream()
                .map(Attendance::createResponse)
                .toList();
    }

    private Attendance updateDateTime(final LocalDateTime dateTime, final Attendance attendance) {
        Attendance updated = attendance.updateDateTime(dateTime);
        updateResult();
        return updated;
    }

    private void updateResult() {
        this.result = new AttendanceResults(attendances.subList(0, attendances.size() - 1));
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

    private void validationAlreadyAttendance(final Attendance attendance) {
        if (!attendance.isDefault()) {
            throw new IllegalArgumentException(ALREADY_ATTENDANCE.get());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Crew that = (Crew) obj;

        return Objects.equals(this.nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}
