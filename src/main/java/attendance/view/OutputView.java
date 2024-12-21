package attendance.view;

import static attendance.global.constant.MessageConstant.DEFAULT_TIME;
import static attendance.global.constant.MessageConstant.NEW_LINE;
import static attendance.global.constant.MessageConstant.OUTPUT_ATTENDANCE_DETAIL;
import static attendance.global.constant.MessageConstant.OUTPUT_DATE_AND_FUNCTION_SELECTION;
import static attendance.global.constant.MessageConstant.OUTPUT_EXPULSION_RISK;
import static attendance.global.constant.MessageConstant.OUTPUT_EXPULSION_RISK_HEADER;
import static attendance.global.constant.MessageConstant.OUTPUT_MODIFIED_RESULT;
import static attendance.global.constant.MessageConstant.OUTPUT_ATTENDANCE_RECORD_HEADER;
import static attendance.global.constant.MessageConstant.OUTPUT_ATTENDANCE_RESULT;
import static attendance.global.constant.MessageConstant.OUTPUT_ATTENDANCE_EVALUATION;
import static java.time.format.TextStyle.FULL;
import static java.util.Locale.KOREA;

import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.AttendanceResultResponse;
import attendance.domain.dto.ExpulsionRiskResponse;
import attendance.domain.dto.ModifiedResponse;
import attendance.domain.dto.RecordResponse;
import attendance.domain.dto.RegisteredResponse;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OutputView {

    public void printDateAndFunctionSelection() {
        LocalDateTime now = DateTimes.now();
        System.out.println(OUTPUT_DATE_AND_FUNCTION_SELECTION.get(
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getDayOfWeek().getDisplayName(FULL, KOREA)
        ));
    }

    public void printRegisteredResult(RegisteredResponse response) {
        System.out.print(NEW_LINE.get());
        printAttendanceDetail(response.register());
    }

    public void printModifiedResult(ModifiedResponse response) {
        System.out.print(NEW_LINE.get());
        printAttendanceDetail(response.before());

        AttendanceResponse newAttendance = response.after();
        System.out.println(OUTPUT_MODIFIED_RESULT.get(
                newAttendance.dateTime().toLocalTime(),
                newAttendance.status()
        ));
    }

    public void printAttendanceRecord(RecordResponse response) {
        System.out.print(NEW_LINE.get());
        System.out.println(OUTPUT_ATTENDANCE_RECORD_HEADER.get(response.nickname()));

        printAttendanceDetails(response.attendances());
        printAttendanceResult(response.attendanceResult());
    }

    public void printExpulsionRisks(ExpulsionRiskResponse response) {
        System.out.println(OUTPUT_EXPULSION_RISK_HEADER.get());
        response.expulsionRisks().forEach(this::printExpulsionRisk);
    }

    private void printExpulsionRisk(RecordResponse response) {
        AttendanceResultResponse attendanceResult = response.attendanceResult();
        System.out.println(OUTPUT_EXPULSION_RISK.get(
                response.nickname(),
                attendanceResult.absence(),
                attendanceResult.perception(),
                attendanceResult.evaluation()
        ));
    }

    private void printAttendanceResult(AttendanceResultResponse response) {
        System.out.print(NEW_LINE.get());
        System.out.println(OUTPUT_ATTENDANCE_RESULT.get(
                response.attendance(),
                response.perception(),
                response.absence()));

        System.out.println(OUTPUT_ATTENDANCE_EVALUATION.get(response.evaluation()));
    }

    private void printAttendanceDetails(final List<AttendanceResponse> attendances) {
        attendances.forEach(attendance -> {
            this.printAttendanceDetail(attendance);
            System.out.print(NEW_LINE.get());
        });
    }

    private void printAttendanceDetail(final AttendanceResponse response) {
        LocalDateTime dateTime = response.dateTime();
        System.out.print(OUTPUT_ATTENDANCE_DETAIL.get(
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getDayOfWeek().getDisplayName(FULL, KOREA),
                formatTime(dateTime.toLocalTime()),
                response.status()));
    }

    private String formatTime(final LocalTime time) {
        if (time.equals(LocalTime.MIN)) {
            return DEFAULT_TIME.get();
        }
        return time.toString();
    }
}
