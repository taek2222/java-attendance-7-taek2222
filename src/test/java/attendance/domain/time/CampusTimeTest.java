package attendance.domain.time;

import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CampusTimeTest {

    @ParameterizedTest(name = "시간: {0}")
    @CsvSource({
            "08:00",
            "23:00",
            "13:00",
            "17:00"
    })
    void 캠퍼스_운영_시간인_경우_TRUE를_반환한다(LocalTime time) {
        // when
        boolean result = CampusTime.isCampusOperateTime(time);

        // then
        Assertions.assertThat(result)
                .isTrue();
    }

    @ParameterizedTest(name = "시간: {0}")
    @CsvSource({
            "07:59",
            "23:01"
    })
    void 캠퍼스_운영_시간이_아닌_경우_FALSE를_반환한다(LocalTime time) {
        // when
        boolean result = CampusTime.isCampusOperateTime(time);

        // then
        Assertions.assertThat(result)
                .isFalse();
    }

}