package attendance.service;

import static attendance.global.util.DateTimeParser.parseDateTime;
import static attendance.global.validation.AttendanceValidator.validateDayOfMonth;
import static attendance.global.validation.AttendanceValidator.validateSchoolDay;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.domain.dto.ModifiedResponse;
import attendance.domain.dto.RegisteredResponse;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceService {

    private static final String REGISTER_FUNCTION = "1";
    private static final String MODIFY_FUNCTION = "2";
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceService(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void processAttendance(String function, Crews crews) {
        if (function.equals(REGISTER_FUNCTION)) {
            processRegister(crews);
        }

        if (function.equals(MODIFY_FUNCTION)) {
            processModify(crews);
        }
    }

    private void processRegister(final Crews crews) {
        validateSchoolDay(DateTimes.now().toLocalDate()); // 등교 휴일 검사
        Crew crew = getCrewForRegister(crews);
        registerCrewAttendance(crew);
    }

    private void processModify(final Crews crews) {
        Crew crew = getCrewForModify(crews);
        LocalDate date = getDateForModify();
        LocalDateTime dateTime = getDateTimeForModify(date);
        modifyCrewAttendance(crew, dateTime);
    }

    private Crew getCrewForRegister(final Crews crews) {
        String nickname = inputView.readRegisterCrewNickname();
        return crews.getCrewByNickname(nickname);
    }

    private void registerCrewAttendance(final Crew crew) {
        LocalDateTime dateTime = getDateTimeForRegister();

        RegisteredResponse response = crew.registerAttendance(dateTime);
        outputView.printRegisteredAttendance(response);
    }

    private Crew getCrewForModify(final Crews crews) {
        String nickname = inputView.readModifyCrewNickname();
        return crews.getCrewByNickname(nickname);
    }

    private LocalDate getDateForModify() {
        int modifyDay = inputView.readModifyDay();
        validateDayOfMonth(modifyDay);

        LocalDate date = DateTimes.now().toLocalDate().withDayOfMonth(modifyDay);
        validateSchoolDay(date);
        return date;
    }

    private LocalDateTime getDateTimeForModify(final LocalDate date) {
        String time = inputView.readModifyTime();
        return parseDateTime(date, time);
    }

    private void modifyCrewAttendance(final Crew crew, final LocalDateTime dateTime) {
        ModifiedResponse response = crew.updateAttendance(dateTime);
        outputView.printModifiedAttendance(response);
    }

    private LocalDateTime getDateTimeForRegister() {
        String input = inputView.readRegisterTime();
        return parseDateTime(DateTimes.now().toLocalDate(), input);
    }
}
