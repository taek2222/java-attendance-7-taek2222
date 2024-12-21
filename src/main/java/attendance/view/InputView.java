package attendance.view;

import static attendance.global.constant.MessageConstant.INPUT_FUNCTION_SELECTION;
import static attendance.view.InputValidator.validateFunctionSelection;
import static attendance.view.InputValidator.validateIsNumeric;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readTimeForModification() {
        System.out.println("언제로 변경하겠습니까?");
        return Console.readLine();
    }

    public int readDayForModification() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        String input = Console.readLine();

        validateIsNumeric(input);
        return Integer.parseInt(input);
    }

    public String readNicknameForModification() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readNickname() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readSchoolStartTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public String readFunctionSelection() {
        System.out.print(INPUT_FUNCTION_SELECTION.get());

        String input = Console.readLine();
        validateFunctionSelection(input);
        return input;
    }
}
