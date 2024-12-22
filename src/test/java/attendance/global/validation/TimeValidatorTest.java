package attendance.global.validation;

import static attendance.global.constant.ErrorMessage.CAMPUS_OPERATING_TIME;
import static attendance.global.constant.ErrorMessage.INVALID_TIME_INPUT;
import static attendance.global.validation.TimeValidator.validateCampusOperateTime;
import static attendance.global.validation.TimeValidator.validateTimeParse;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TimeValidatorTest {

    @ParameterizedTest(name = "시간: {0}")
    @CsvSource({
            "33:23",
            "-00:00",
            "24:00"
    })
    void 잘못된_시간_입력인_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> validateTimeParse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_TIME_INPUT.get());
    }

    @ParameterizedTest(name = "시간: {0}")
    @CsvSource({
            "00:00",
            "05:00",
            "23:30",
            "23:01",
            "07:59"
    })
    void 캠퍼스_운영_시간이_아닌_경우_예외가_발생한다(LocalTime time) {
        assertThatThrownBy(() -> validateCampusOperateTime(time))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAMPUS_OPERATING_TIME.get());
    }
}