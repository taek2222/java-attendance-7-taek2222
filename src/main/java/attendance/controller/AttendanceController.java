package attendance.controller;

import attendance.domain.Crews;
import attendance.service.AttendanceService;
import attendance.service.InitService;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AttendanceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InitService initService;
    private final AttendanceService attendanceService;

    public AttendanceController(final InputView inputView,
                                final OutputView outputView,
                                final InitService initService,
                                final AttendanceService attendanceService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.initService = initService;
        this.attendanceService = attendanceService;
    }

    public void run() {
        Crews crews = initService.initializeCrewsFromFile();

        while (true) {
            String function = readFunctionSelection();
            if (function.equals("Q")) {
                break;
            }
            attendanceService.processAttendance(function, crews);
        }
    }

    private String readFunctionSelection() {
        outputView.printDateAndFunctionSelection();
        return inputView.readFunctionSelection();
    }
}
