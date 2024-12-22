package attendance.domain.attendance;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AttendanceTest {

    @ParameterizedTest(name = "날짜/시간: {0}, 예상 결과: {1}")
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
        assertThat(result)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "날짜/시간: {0}, 날짜: {1}, 예상 결과: {2}")
    @CsvSource({
            "2024-12-04T15:00, 2024-12-04, true",
            "2024-12-04T15:00, 2024-12-06, false"
    })
    void 동일_날짜_여부에_따라_참_거짓을_반환한다(LocalDateTime dateTime, LocalDate date, boolean expected) {
        // given
        Attendance attendance = new Attendance(dateTime);

        // when
        boolean result = attendance.isSameDate(date);

        // then
        assertThat(result)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "날짜/시간: {0}, 출결 상황: {1}, 예상 결과: {2}")
    @CsvSource({
            "2024-12-04T10:00, ABSENCE, false",
            "2024-12-04T10:00, ATTENDANCE, true",
            "2024-12-04T10:10, PERCEPTION, true",
            "2024-12-04T10:10, ATTENDANCE, false"

    })
    void 동일_출석_상황에_따라_참_거짓을_반환한다(LocalDateTime dateTime, String status, boolean expected) {
        // given
        Attendance attendance = new Attendance(LocalDateTime.of(2024, 12, 2, 0, 0));
        attendance.updateDateTime(dateTime);

        AttendanceStatus attendanceStatus = AttendanceStatus.valueOf(status);

        // when
        boolean result = attendance.isSameStatus(attendanceStatus);

        // then
        assertThat(result)
                .isEqualTo(expected);
    }
}