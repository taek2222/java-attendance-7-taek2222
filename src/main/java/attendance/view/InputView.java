package attendance.view;

import static attendance.global.constant.MessageConstant.INPUT_DAY_FOR_MODIFICATION;
import static attendance.global.constant.MessageConstant.INPUT_FUNCTION_SELECTION;
import static attendance.global.constant.MessageConstant.INPUT_NICKNAME;
import static attendance.global.constant.MessageConstant.INPUT_NICKNAME_FOR_MODIFICATION;
import static attendance.global.constant.MessageConstant.INPUT_SCHOOL_START_TIME;
import static attendance.global.constant.MessageConstant.INPUT_TIME_FOR_MODIFICATION;
import static attendance.view.InputValidator.validateFunctionSelection;
import static attendance.view.InputValidator.validateIsNumeric;

import attendance.global.constant.MessageConstant;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readTimeForModification() {
        return printMessageAndReadInput(INPUT_TIME_FOR_MODIFICATION);
    }

    public int readDayForModification() {
        String input = printMessageAndReadInput(INPUT_DAY_FOR_MODIFICATION);
        validateIsNumeric(input);
        return Integer.parseInt(input);
    }

    public String readNicknameForModification() {
        return printMessageAndReadInput(INPUT_NICKNAME_FOR_MODIFICATION);
    }

    public String readNickname() {
        return printMessageAndReadInput(INPUT_NICKNAME);
    }

    public String readSchoolStartTime() {
        return printMessageAndReadInput(INPUT_SCHOOL_START_TIME);
    }

    public String readFunctionSelection() {
        String input = printMessageAndReadInput(INPUT_FUNCTION_SELECTION);
        validateFunctionSelection(input);
        return input;
    }

    private String printMessageAndReadInput(MessageConstant message) {
        System.out.println(message.get());
        return Console.readLine();
    }
}
