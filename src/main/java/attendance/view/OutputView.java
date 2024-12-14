package attendance.view;

import static attendance.global.constant.MessageConstant.NEW_LINE;

import attendance.domain.dto.AttendanceResponse;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {
    
    public void printCrewModified(AttendanceResponse oldCrew, AttendanceResponse newCrew) {
        printCrewInfo(oldCrew, oldCrew.dateTime());
        System.out.printf(" -> %s %s 수정 완료!",
                newCrew.crewResponses().getFirst().time(),
                newCrew.crewResponses().getFirst().attendance());
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
                response.crewResponses().getFirst().time(),
                response.crewResponses().getFirst().attendance()
                );
    }

    public void printChoiceFunction() {
        LocalDateTime now = DateTimes.now();
        System.out.printf("오늘은 %s월 %s일 %s입니다. 기능을 선택해 주세요." + NEW_LINE.get(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA));
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
