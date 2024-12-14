package attendance.view;

import static attendance.global.constant.MessageConstant.NEW_LINE;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {

    public void printChoiceFunction() {
        LocalDateTime now = DateTimes.now().minusDays(1);
        System.out.printf("오늘은 %s월 %s일 %s입니다. 기능을 선택해 주세요." + NEW_LINE.get(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA));

        System.out.println("""
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료
                """);
    }



    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
