package attendance.domain;

import static attendance.global.constant.ErrorMessage.AFTER_CLASS_TIME;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AttendanceTypeTest {

    @ParameterizedTest(name = "시간: {0}")
    @CsvSource({
            "18:01",
            "19:30"
    })
    void 교육_시간이_아닌_경우_예외가_발생한다(LocalTime time) {
        // given
        LocalDateTime dateTime = LocalDateTime.now().with(time);

        // when & then
        assertThatThrownBy(() -> AttendanceType.calculateTimeBetween(dateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AFTER_CLASS_TIME.get());
    }
}