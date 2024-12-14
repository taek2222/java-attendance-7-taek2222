package attendance.controller;


import attendance.global.util.AttendancesParser;
import attendance.global.util.FileUtil;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> attendances = FileUtil.readFile("attendances.csv");
        AttendancesParser.parseAttendances(attendances);
    }
}
