package attendance.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {

    private static final List<String> FUNCTION = List.of("1", "2", "3", "4", "Q");

    public String readSchoolStartTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public String readNickname() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readChoiceFunction() {
        System.out.print("""
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료
                """);
        String input = Console.readLine();
        validateChoiceFunction(input);
        System.out.println();
        return input;
    }

    private void validateChoiceFunction(String input) {
        if (!FUNCTION.contains(input)) {
            throw new IllegalArgumentException();
        }
    }

}
