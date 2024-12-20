package attendance.view;

import static attendance.global.constant.MessageConstant.INPUT_FUNCTION_SELECTION;
import static attendance.view.InputValidator.validateFunctionSelection;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readFunctionSelection() {
        System.out.print(INPUT_FUNCTION_SELECTION.get());

        String input = Console.readLine();
        validateFunctionSelection(input);
        return input;
    }

    public String readModifyCrewNickname() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readAttendanceCrewNickname() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readAttendanceTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }
}
