package attendance.domain.time;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HolidayTest {

    @ParameterizedTest(name = "날짜: {0}")
    @CsvSource({
            "2024-12-01",
            "2024-12-08",
            "2024-12-25"
    })
    void 주말_혹은_공휴일인_경우_TRUE를_반환한다(LocalDate date) {
        // when
        boolean result = Holiday.isHoliday(date);

        // then
        Assertions.assertThat(result)
                .isTrue();
    }

    @ParameterizedTest(name = "날짜: {0}")
    @CsvSource({
            "2024-12-02",
            "2024-12-03",
            "2024-12-23"
    })
    void 주말_혹은_공휴일이_아닌_경우_FALSE를_반환한다(LocalDate date) {
        // when
        boolean result = Holiday.isHoliday(date);

        // then
        Assertions.assertThat(result)
                .isFalse();
    }
}