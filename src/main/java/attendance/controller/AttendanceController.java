package attendance.controller;


import attendance.domain.Attendance;
import attendance.domain.Attendances;
import attendance.domain.Crew;
import attendance.domain.CrewNickname;
import attendance.global.util.AttendancesParser;
import attendance.global.util.CrewParser;
import attendance.global.util.DateTimeParser;
import attendance.global.util.FileUtil;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Attendances attendances = initAttendances();
        updateFileAttendances(attendances);

        outputView.printChoiceFunction();
        String function = inputView.readChoiceFunction();

        if (function.equals("1")) {
            String nickname = inputView.readNickname();
            String startTime = inputView.readSchoolStartTime();
            LocalDateTime now = DateTimes.now().minusDays(1);

            LocalDateTime dateTime = DateTimeParser.parseCrew(LocalDate.from(now), startTime);
            Crew crew = CrewParser.parseCrew(nickname, dateTime);
            attendances.updateNewAttendance(crew, dateTime);
            Attendance attendance = attendances.findAttendanceByDate(dateTime);
            outputView.printCrewAttendanceStatus(attendance.createResponseByCrew(crew));
        }
    }

    private void updateFileAttendances(final Attendances attendances) {
        List<String> fileAttendances = FileUtil.readFile("attendances.csv");
        AttendancesParser.parseAndUpdateAttendances(attendances, fileAttendances);
    }


    private Attendances initAttendances() {
        List<Attendance> attendances = new ArrayList<>();
        LocalDateTime now = DateTimes.now();
        LocalDateTime start = LocalDateTime.of(2024, 12, 1, 0, 0, 0);
        while (!start.toLocalDate().isEqual(now.toLocalDate())) {
            Attendance attendance = new Attendance(start, CrewNickname.generateCrewDefault());
            attendances.add(attendance);
            start = start.plusDays(1);
        }
        return new Attendances(attendances);
    }
}
