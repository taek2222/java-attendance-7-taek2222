package attendance.global.validation;

import static attendance.global.constant.ErrorMessage.INVALID_INPUT;
import static attendance.global.constant.ErrorMessage.NOT_MODIFY_DAY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AttendanceValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 35})
    void 잘못된_날짜를_입력한_경우_예외가_발생한다(int day) {
        // when & then
        assertThatThrownBy(() -> AttendanceValidator.validateDayOfMonth(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_INPUT.get());
    }

    @Test
    void 미래_날짜를_수정한_경우_예외가_발생한다() {
        // given
        int futureDay = DateTimes.now().getDayOfMonth() + 1;

        // when & then
        assertThatThrownBy(() -> AttendanceValidator.validateDayOfMonth(futureDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MODIFY_DAY.get());
    }
}