package attendance.service;

import static attendance.global.constant.ErrorMessage.CAMPUS_OPERATING_TIME;
import static attendance.global.constant.ErrorMessage.INVALID_TIME_INPUT;
import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;

import attendance.domain.CampusTime;
import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.DateTimeException;
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

            try {
                LocalTime time = LocalTime.parse(input);
                validateTime(time);
            } catch (DateTimeException e) {
                throw new IllegalArgumentException(INVALID_TIME_INPUT.get());
            }

        }
    }

    private void validateTime(final LocalTime time) {
        if (!CampusTime.isCampusOperateTime(time)){
            throw new IllegalArgumentException(CAMPUS_OPERATING_TIME.get());
        }
    }

    private void validateCrew(final Crew crew) {
        if (crew == null) {
            throw new IllegalArgumentException(NOT_FOUND_NICKNAME.get());
        }
    }
}
