package attendance.controller;


import attendance.domain.Attendance;
import attendance.domain.Attendances;
import attendance.domain.CrewNickname;
import attendance.global.util.AttendancesParser;
import attendance.global.util.FileUtil;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
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
        inputView.readChoiceFunction();
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
