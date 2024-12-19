package attendance.view;

import static org.junit.jupiter.api.Assertions.*;

import attendance.global.constant.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"5", "S", "^"})
    void 기능_입력이_아닌_경우_예외가_발생한다(String input) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateFunctionSelection(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.get());
    }

}