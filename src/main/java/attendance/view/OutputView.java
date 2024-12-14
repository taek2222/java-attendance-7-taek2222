package attendance.view;

import static attendance.global.constant.MessageConstant.NEW_LINE;

import attendance.domain.dto.AttendanceResponse;
import attendance.domain.dto.AttendancesResponse;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class OutputView {

    public void printCrewInfos(AttendancesResponse response) {
        List<AttendanceResponse> responses = response.attendanceResponses();

        for (AttendanceResponse attendanceResponse : responses) {
            this.printCrewInfo(
                    attendanceResponse,
                    attendanceResponse.dateTime()
            );
        }
    }
    
    public void printCrewModified(AttendanceResponse oldCrew, AttendanceResponse newCrew) {
        printCrewInfo(oldCrew, oldCrew.dateTime());
        System.out.printf(" -> %s %s 수정 완료!",
                newCrew.crewResponses().time(),
                newCrew.crewResponses().attendance());
    }

    public void printCrewAttendanceStatus(AttendanceResponse response) {
        System.out.println();

        LocalDateTime dateTime = response.dateTime();
        printCrewInfo(response, dateTime);
    }

    private void printCrewInfo(final AttendanceResponse response, final LocalDateTime dateTime) {
        System.out.printf("%s월 %s일 %s %s %s",
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA),
                response.crewResponses().time(),
                response.crewResponses().attendance()
                );
    }

    public void printChoiceFunction() {
        LocalDateTime now = DateTimes.now();
        System.out.printf("오늘은 %s월 %s일 %s입니다. 기능을 선택해 주세요." + NEW_LINE.get(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA));
    }
}
