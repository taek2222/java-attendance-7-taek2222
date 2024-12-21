package attendance.domain;

import static attendance.global.constant.ErrorMessage.AFTER_CLASS_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import attendance.domain.time.ClassTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ClassTimeTest {

    @ParameterizedTest(name = "시간: {0}")
    @CsvSource({
            "18:01",
            "19:30"
    })
    void 교육_시간이_아닌_경우_예외가_발생한다(LocalTime time) {
        // given
        LocalDate date = LocalDate.of(2024, 12, 20);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // when & then
        assertThatThrownBy(() -> ClassTime.calculateTimeBetween(dateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AFTER_CLASS_TIME.get());
    }

    @ParameterizedTest(name = "시간: {0}")
    @CsvSource({
            "10:02, 2",
            "09:55, -5"
    })
    void 교육_시간의_차이를_계산해_반환한다(LocalTime time, int expected) {
        // given
        LocalDate date = LocalDate.of(2024, 12, 20);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // when
        int result = ClassTime.calculateTimeBetween(dateTime);

        // then
        assertThat(result)
                .isEqualTo(expected);
    }
}