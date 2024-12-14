package attendance.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AttendanceTypeTest {

    @Test
    void 정상_출석인_경우_출석을_반환한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 16, 9, 50, 0);

        // when
        String result = AttendanceType.evaluateAttendance(dateTime);

        // then
        Assertions.assertThat(result)
                .isEqualTo("출석");
    }

    @Test
    void 지각_시각인_경우_지각을_반환한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 17, 10, 6, 0);

        // when
        String result = AttendanceType.evaluateAttendance(dateTime);

        // then
        Assertions.assertThat(result)
                .isEqualTo("지각");
    }

    @Test
    void 결석_시각인_경우_결석을_반환한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 16, 17, 50, 0);

        // when
        String result = AttendanceType.evaluateAttendance(dateTime);

        // then
        Assertions.assertThat(result)
                .isEqualTo("결석");
    }

}