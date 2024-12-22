package attendance.domain;

import static attendance.global.constant.ErrorMessage.ALREADY_ATTENDANCE;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceResults;
import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.CrewResponse;
import attendance.domain.dto.ModifiedResponse;
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

    public RegisteredResponse registerAttendance(final LocalDateTime dateTime) {
        Attendance attendance = findAttendanceByDate(dateTime.toLocalDate());
        validationAlreadyAttendance(attendance);
        updateDateTime(dateTime, attendance);
        return new RegisteredResponse(
                attendance.createResponse()
        );
    }

    public ModifiedResponse updateAttendance(final LocalDateTime dateTime) {
        Attendance oldAttendance = findAttendanceByDate(dateTime.toLocalDate());
        AttendanceResponse oldAttendanceResponse = oldAttendance.createResponse();

        Attendance newAttendance = updateDateTime(dateTime, oldAttendance);
        return new ModifiedResponse(
                oldAttendanceResponse,
                newAttendance.createResponse()
        );
    }

    public CrewResponse createResponse() {
        return new CrewResponse(
                nickname,
                getAttendanceResponses(),
                result.createResponse()
        );
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

    public boolean isSameNickname(final String nickname) {
        return this.nickname.equals(nickname);
    }

    private Attendance findAttendanceByDate(final LocalDate date) {
        return attendances.stream()
                .filter(attendance -> attendance.isSameDate(date))
                .findFirst()
                .orElseThrow();
    }

    private void validationAlreadyAttendance(final Attendance attendance) {
        if (!attendance.isInitialTime()) {
            throw new IllegalArgumentException(ALREADY_ATTENDANCE.get());
        }
    }

    private Attendance updateDateTime(final LocalDateTime dateTime, final Attendance attendance) {
        Attendance updated = attendance.updateDateTime(dateTime);
        updateResult();
        return updated;
    }

    private void updateResult() {
        this.result = new AttendanceResults(attendances);
    }

    private List<AttendanceResponse> getAttendanceResponses() {
        List<Attendance> subbed = attendances.subList(0, attendances.size() - 1);
        return subbed.stream()
                .map(Attendance::createResponse)
                .toList();
    }

    private void generateDefaultAttendances() {
        LocalDateTime now = DateTimes.now();
        LocalDateTime defaultDateTime = now.withDayOfMonth(1).with(LocalTime.MIN);

        while (!defaultDateTime.isAfter(now)) {
            if (Holiday.isHoliday(defaultDateTime.toLocalDate())) {
                defaultDateTime = defaultDateTime.plusDays(1);
                continue;
            }
            attendances.add(new Attendance(defaultDateTime));
            defaultDateTime = defaultDateTime.plusDays(1);
        }
    }
}
