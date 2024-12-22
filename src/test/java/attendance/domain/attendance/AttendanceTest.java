package attendance.domain.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AttendanceTest {

    @ParameterizedTest(name = "시간: {0}, 예상 결과: {1}")
    @CsvSource({
            "2024-12-04T00:00, true",
            "2024-12-04T15:00, false"
    })
    void 초기_설정한_시간에_따라_참_거짓을_반환한다(LocalDateTime dateTime, boolean expected) {
        // given
        Attendance attendance = new Attendance(dateTime);

        // when
        boolean result = attendance.isInitialTime();

        // then
        Assertions.assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    void 동일한_날짜인_경우_TRUE를_반환한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 4, 15, 0);
        Attendance attendance = new Attendance(dateTime);

        LocalDate date = LocalDate.of(2024, 12, 4);

        // when
        boolean result = attendance.isSameDate(date);

        // then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    void 동일한_출석_상황인_경우_TRUE를_반환한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 4, 15, 0);
        Attendance attendance = new Attendance(dateTime);

        AttendanceStatus status = AttendanceStatus.ABSENCE;

        // when
        boolean result = attendance.isSameStatus(status);

        // then
        Assertions.assertThat(result)
                .isTrue();
    }
}