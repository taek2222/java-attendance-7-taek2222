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
}
