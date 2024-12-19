package attendance.controller;

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
        List<String> readAttendances = FileUtil.readFile("attendances.csv");

        String function = readFunctionSelection();
    }

    private String readFunctionSelection() {
        outputView.printDateAndFunctionSelection();
        return inputView.readFunctionSelection();
    }
}
