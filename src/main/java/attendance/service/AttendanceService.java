package attendance.service;

import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.global.util.TimeParser;
import attendance.view.InputView;
import attendance.view.OutputView;
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
            String nickname = inputView.readAttendanceNickname();
            Crew crew = crews.findCrewByNickname(nickname);
            validateCrew(crew);

            String input = inputView.readAttendanceTime();
            LocalTime time = TimeParser.parseTime(input);
        }
    }

    private void validateCrew(final Crew crew) {
        if (crew == null) {
            throw new IllegalArgumentException(NOT_FOUND_NICKNAME.get());
        }
    }
}
