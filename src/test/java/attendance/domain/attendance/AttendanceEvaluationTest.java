package attendance.domain.attendance;

import static attendance.domain.attendance.AttendanceEvaluation.evaluateAttendance;
import static attendance.domain.attendance.AttendanceEvaluation.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AttendanceEvaluationTest {

    @ParameterizedTest(name = "횟수 - 지각: {0}, 결석: {1}, 예상 평가값: {2}")
    @CsvSource({
            "0, 6, WEEDING",
            "3, 5, WEEDING",
            "0, 5, INTERVIEW",
            "3, 4, INTERVIEW",
            "0, 3, INTERVIEW",
            "3, 2, INTERVIEW",
            "2, 2, WARNING",
            "0, 2, WARNING",
            "3, 1, WARNING",
            "2, 1, SINCERITY",
            "0, 1, SINCERITY",
            "0, 0, SINCERITY"
    })
    void 지각과_결석_횟수에_따라_상태를_판정한다(int perception, int absence, String evaluationExpected) {
        // given
        AttendanceEvaluation expected = valueOf(evaluationExpected);

        // when
        AttendanceEvaluation result = evaluateAttendance(perception, absence);

        // then
        assertThat(result)
                .isEqualTo(expected);
    }
}
