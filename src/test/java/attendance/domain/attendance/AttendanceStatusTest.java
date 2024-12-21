package attendance.domain.attendance;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AttendanceStatusTest {

    @ParameterizedTest
    @CsvSource({
            "09:55, ATTENDANCE",
            "10:05, ATTENDANCE",
            "10:06, PERCEPTION",
            "10:30, PERCEPTION",
            "10:31, ABSENCE",
            "11:50, ABSENCE"
    })
    void 시간에_따라_출결_상황을_반환한다(LocalTime time, String expected) {
        // given
        LocalDate date = LocalDate.of(2024, 12, 20);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        AttendanceStatus expectedStatus = AttendanceStatus.valueOf(expected);

        // when
        AttendanceStatus result = AttendanceStatus.evaluateAttendanceStatus(dateTime);

        // then
        assertThat(result)
                .isEqualTo(expectedStatus);
    }

}