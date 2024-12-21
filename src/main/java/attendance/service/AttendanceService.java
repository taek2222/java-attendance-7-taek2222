package attendance.service;

import static attendance.global.util.DateTimeParser.parseDateTime;
import static attendance.global.validation.DateValidator.validateDayOfMonth;
import static attendance.global.validation.DateValidator.validateSchoolDay;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceService {

    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceService(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void processRegister(final Crews crews) {
        validateSchoolDay(DateTimes.now().toLocalDate()); // 등교 휴일 검사
        Crew crew = getCrew(crews, false);
        registerCrewAttendance(crew);
    }

    public void processModify(final Crews crews) {
        Crew crew = getCrew(crews, true);
        LocalDate date = getDateForModify();
        LocalDateTime dateTime = getDateTimeForModify(date);
        outputView.printModifiedResult(crew.updateAttendance(dateTime));
    }

    public void processRecord(final Crews crews) {
        Crew crew = getCrew(crews, false);
        outputView.printAttendanceRecord(crew.createResponse());
    }

    public void processExpulsionRiskCheck(final Crews crews) {
        outputView.printExpulsionRisks(crews.createExpulsionRiskResponse());
    }

    private Crew getCrew(final Crews crews, final boolean isModification) {
        String nickname = readNickname(isModification);
        return crews.findCrewByNickname(nickname);
    }

    private String readNickname(final boolean isModification) {
        if (isModification) {
            return inputView.readNicknameForModification();
        }
        return inputView.readNickname();
    }

    private void registerCrewAttendance(final Crew crew) {
        LocalDateTime dateTime = getDateTimeForRegister();
        outputView.printRegisteredResult(crew.registerAttendance(dateTime));
    }

    private LocalDateTime getDateTimeForRegister() {
        String input = inputView.readSchoolStartTime();
        return parseDateTime(DateTimes.now().toLocalDate(), input);
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
}
