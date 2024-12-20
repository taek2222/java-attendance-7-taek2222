package attendance.view;

import static attendance.global.constant.MessageConstant.NEW_LINE;
import static attendance.global.constant.MessageConstant.OUTPUT_ATTENDANCE_INFO;
import static attendance.global.constant.MessageConstant.OUTPUT_DATE_AND_FUNCTION_SELECTION;
import static attendance.global.constant.MessageConstant.OUTPUT_MODIFIED_ATTENDANCE;
import static java.time.format.TextStyle.FULL;
import static java.util.Locale.KOREA;

import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.ModifiedAttendanceResponse;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;

public class OutputView {
    
    public void printModifiedAttendance(ModifiedAttendanceResponse response) {
        System.out.printf(NEW_LINE.get());
        printAttendanceInfo(response.oldAttendance());

        AttendanceResponse newAttendance = response.newAttendance();
        System.out.println(OUTPUT_MODIFIED_ATTENDANCE.get(
                newAttendance.dateTime().toLocalTime(),
                newAttendance.attendanceStatus()
        ));
    }

    public void printAttendanceCheck(AttendanceResponse response) {
        System.out.printf(NEW_LINE.get());
        printAttendanceInfo(response);
    }

    private void printAttendanceInfo(final AttendanceResponse response) {
        LocalDateTime dateTime = response.dateTime();
        System.out.print(OUTPUT_ATTENDANCE_INFO.get(
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getDayOfWeek().getDisplayName(FULL, KOREA),
                dateTime.toLocalTime(),
                response.attendanceStatus()
        ));
    }

    public void printDateAndFunctionSelection() {
        LocalDateTime now = DateTimes.now();
        System.out.println(OUTPUT_DATE_AND_FUNCTION_SELECTION.get(
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getDayOfWeek().getDisplayName(FULL, KOREA)
                )
        );
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
