package attendance.service;

import static attendance.global.util.DateTimeParser.parseDateTime;
import static attendance.global.validation.DateValidator.validateDayOfMonth;
import static attendance.global.validation.DateValidator.validateSchoolDay;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.domain.dto.ModifiedResponse;
import attendance.domain.dto.RecordResponse;
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

        if (function.equals("3")) {
            Crew crew = getCrew(crews);
            RecordResponse response = crew.createResponse();
            outputView.printAttendanceRecord(response);
        }
    }

    private void processRegister(final Crews crews) {
        validateSchoolDay(DateTimes.now().toLocalDate()); // 등교 휴일 검사
        Crew crew = getCrew(crews);
        registerCrewAttendance(crew);
    }

    private void processModify(final Crews crews) {
        Crew crew = getCrewForModify(crews);
        LocalDate date = getDateForModify();
        LocalDateTime dateTime = getDateTimeForModify(date);
        modifyCrewAttendance(crew, dateTime);
    }

    private Crew getCrew(final Crews crews) {
        String nickname = inputView.readNickname();
        return crews.getCrewByNickname(nickname);
    }

    private void registerCrewAttendance(final Crew crew) {
        LocalDateTime dateTime = getDateTimeForRegister();

        RegisteredResponse response = crew.registerAttendance(dateTime);
        outputView.printRegisteredResult(response);
    }

    private Crew getCrewForModify(final Crews crews) {
        String nickname = inputView.readNicknameForModification();
        return crews.getCrewByNickname(nickname);
    }

    private LocalDate getDateForModify() {
        int modifyDay = inputView.readDayForModification();
        validateDayOfMonth(modifyDay);

        LocalDate date = DateTimes.now().toLocalDate().withDayOfMonth(modifyDay);
        validateSchoolDay(date);
        return date;
    }

    private LocalDateTime getDateTimeForModify(final LocalDate date) {
        String time = inputView.readTimeForModification();
        return parseDateTime(date, time);
    }

    private void modifyCrewAttendance(final Crew crew, final LocalDateTime dateTime) {
        ModifiedResponse response = crew.updateAttendance(dateTime);
        outputView.printModifiedResult(response);
    }

    private LocalDateTime getDateTimeForRegister() {
        String input = inputView.readSchoolStartTime();
        return parseDateTime(DateTimes.now().toLocalDate(), input);
    }
}
