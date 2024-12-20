package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
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

    public void updateAttendance(LocalDateTime dateTime) {
        Attendance findAttendance = attendances.stream()
                .filter(attendance -> attendance.isSameDate(dateTime.toLocalDate()))
                .findFirst()
                .orElseThrow();

        findAttendance.updateDateTime(dateTime);
    }

    public boolean isSameNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    private void generateDefaultAttendances() {
        LocalDateTime nowDateTime = DateTimes.now();
        LocalDateTime defaultDateTime = nowDateTime.withDayOfMonth(1).with(LocalTime.MIN);

        while (defaultDateTime.isBefore(nowDateTime)) {
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
}
