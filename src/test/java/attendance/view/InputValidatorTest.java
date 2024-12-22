package attendance.view;

import static attendance.global.constant.ErrorMessage.INVALID_INPUT;
import static attendance.view.InputValidator.validateFunctionSelection;
import static attendance.view.InputValidator.validateIsNumeric;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest(name = "입력: {0}")
    @ValueSource(strings = {"1", "2", "3", "4", "Q"})
    void 기능_입력인_경우_예외가_발생하지_않는다(String input) {
        assertDoesNotThrow(() -> validateFunctionSelection(input));
    }

    @ParameterizedTest(name = "입력: {0}")
    @ValueSource(strings = {"5", "S", "^"})
    void 기능_입력이_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> validateFunctionSelection(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_INPUT.get());
    }

    @ParameterizedTest(name = "입력: {0}")
    @ValueSource(strings = {"A", "S", "-"})
    void 숫자_입력이_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> validateIsNumeric(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_INPUT.get());
    }
}