package attendance.global.validation;

import static attendance.global.constant.ErrorMessage.INVALID_INPUT;
import static attendance.global.constant.ErrorMessage.NOT_MODIFY_DAY;
import static attendance.global.constant.ErrorMessage.NOT_SCHOOL_DAY;
import static attendance.global.validation.DateValidator.validateDayOfMonth;
import static attendance.global.validation.DateValidator.validateSchoolDay;
import static java.time.format.TextStyle.FULL;
import static java.util.Locale.KOREA;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 35})
    void 잘못된_날짜를_입력한_경우_예외가_발생한다(int day) {
        // when & then
        assertThatThrownBy(() -> validateDayOfMonth(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_INPUT.get());
    }

    @Test
    void 미래_날짜를_수정한_경우_예외가_발생한다() {
        // given
        int futureDay = DateTimes.now().getDayOfMonth() + 1;

        // when & then
        assertThatThrownBy(() -> validateDayOfMonth(futureDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_MODIFY_DAY.get());
    }

    @ParameterizedTest(name = "날짜: {0}")
    @CsvSource({
            "2024-12-01",
            "2024-12-21",
            "2024-12-25"
    })
    void 주말_또는_공휴일인_경우_예외가_발생한다(LocalDate date) {
        assertThatThrownBy(() -> validateSchoolDay(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_SCHOOL_DAY.get(
                        date.getMonthValue(),
                        date.getDayOfMonth(),
                        date.getDayOfWeek().getDisplayName(FULL, KOREA)
                ));
    }
}