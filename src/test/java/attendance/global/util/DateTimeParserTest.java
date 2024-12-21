package attendance.global.util;

import static attendance.global.util.DateTimeParser.parseDateTime;
import static attendance.global.util.DateTimeParser.parseTime;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DateTimeParserTest {

    @ParameterizedTest(name = "입력 시간: {0}, 예상 결과값: {1}")
    @CsvSource({
            "15:32, 15:32",
            "12:20, 12:20"
    })
    void 입력_시간값을_파싱해_반환한다(String input, LocalTime expected) {
        // when
        LocalTime result = parseTime(input);

        // then
        assertThat(result)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "날짜: {0}, 입력 시간: {1}, 예상 결과값: {2}")
    @CsvSource({
            "2024-12-13, 10:08, 2024-12-13T10:08",
            "2024-12-06, 10:07, 2024-12-06T10:07"
    })
    void 날짜와_입력_시간값을_파싱해_반환한다(LocalDate date, String input, LocalDateTime expected) {
        // when
        LocalDateTime result = parseDateTime(date, input);

        // then
        assertThat(result)
                .isEqualTo(expected);
    }
}