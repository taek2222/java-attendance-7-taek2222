package attendance.controller;

import attendance.domain.Crews;
import attendance.service.AttendanceService;
import attendance.service.InitService;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AttendanceController {

    private static final String QUIT_COMMAND = "Q";
    
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
        processAttendanceSystem(crews);
    }

    private void processAttendanceSystem(final Crews crews) {
        while (true) {
            String function = readFunctionSelection();
            if (isQuitCommand(function)) {
                break;
            }
            attendanceService.processAttendance(function, crews);
        }
    }

    private String readFunctionSelection() {
        outputView.printDateAndFunctionSelection();
        return inputView.readFunctionSelection();
    }

    private boolean isQuitCommand(final String function) {
        return function.equals(QUIT_COMMAND);
    }
}
