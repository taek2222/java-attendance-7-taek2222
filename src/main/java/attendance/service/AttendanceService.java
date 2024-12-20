package attendance.service;

import static attendance.global.validation.AttendanceValidator.validateCrew;
import static attendance.global.validation.AttendanceValidator.validateDay;
import static attendance.global.validation.AttendanceValidator.validateSchoolDay;

import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.domain.dto.ModifiedAttendanceResponse;
import attendance.global.util.TimeParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceService {

    private static final String CHECK_FUNCTION = "1";
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceService(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void processAttendance(String function, Crews crews) {
        if (function.equals(CHECK_FUNCTION)) {
            processAttendanceCheck(crews);
        }

        if (function.equals("2")) {
            String nickname = inputView.readModifyCrewNickname();
            Crew crew = getCrewByNickname(crews, nickname);

            int modifyDay = inputView.readModifyDay();
            validateDay(modifyDay);
            LocalDate date = DateTimes.now().toLocalDate().withDayOfMonth(modifyDay);
            validateSchoolDay(date);

            String time = inputView.readModifyTime();
            LocalDateTime dateTime = parseDateTime(date, time);

            ModifiedAttendanceResponse response = crew.updateAttendance(dateTime);
            outputView.printModifiedAttendance(response);
        }
    }

    private void processAttendanceCheck(final Crews crews) {
        LocalDateTime now = DateTimes.now();
        validateSchoolDay(now.toLocalDate());

        String nickname = inputView.readAttendanceCrewNickname();
        Crew crew = getCrewByNickname(crews, nickname);

        String input = inputView.readAttendanceTime();
        LocalDateTime dateTime = parseDateTime(now.toLocalDate(), input);

        Attendance attendance = crew.registerAttendance(dateTime);
        outputView.printAttendanceCheck(attendance.createResponse());
    }

    private LocalDateTime parseDateTime(final LocalDate date, final String inputTime) {
        LocalTime time = TimeParser.parseTime(inputTime);
        return LocalDateTime.of(date, time);
    }

    private Crew getCrewByNickname(final Crews crews, final String nickname) {
        Crew crew = crews.findCrewByNickname(nickname);
        validateCrew(crew);
        return crew;
    }
}
