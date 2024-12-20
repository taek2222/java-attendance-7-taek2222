package attendance.service;

import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;
import static attendance.global.constant.ErrorMessage.NOT_SCHOOL_DAY;
import static java.time.format.TextStyle.FULL;
import static java.util.Locale.KOREA;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.domain.Holiday;
import attendance.global.util.TimeParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceService {

    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceService(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void processAttendance(String function, Crews crews) {
        if (function.equals("1")) {
            LocalDateTime now = DateTimes.now();
            validationSchoolDay(now);

            String nickname = inputView.readAttendanceNickname();
            Crew crew = crews.findCrewByNickname(nickname);
            validateCrew(crew);

            String input = inputView.readAttendanceTime();
            LocalTime time = TimeParser.parseTime(input);

            LocalDateTime dateTime = now.with(time);

            crew.registerAttendance(dateTime);
        }
    }

    private void validationSchoolDay(final LocalDateTime dateTime) {
        if (Holiday.isHoliday(dateTime)) {
            throw new IllegalArgumentException(NOT_SCHOOL_DAY.get(
                    dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dateTime.getDayOfWeek().getDisplayName(FULL, KOREA)
            ));
        }
    }

    private void validateCrew(final Crew crew) {
        if (crew == null) {
            throw new IllegalArgumentException(NOT_FOUND_NICKNAME.get());
        }
    }
}
