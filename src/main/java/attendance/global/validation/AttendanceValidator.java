package attendance.global.validation;

import static attendance.global.constant.ErrorMessage.INVALID_INPUT;
import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;
import static attendance.global.constant.ErrorMessage.NOT_MODIFY_DAY;
import static attendance.global.constant.ErrorMessage.NOT_SCHOOL_DAY;
import static java.time.format.TextStyle.FULL;
import static java.util.Locale.KOREA;

import attendance.domain.Crew;
import attendance.domain.Holiday;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DateTimeException;
import java.time.LocalDate;

public class AttendanceValidator {

    public static void validateDayOfMonth(final int day) {
        validateParsableDay(day);
        validateFutureDay(day);
    }

    public static void validateSchoolDay(final LocalDate date) {
        if (Holiday.isHoliday(date)) {
            throw new IllegalArgumentException(NOT_SCHOOL_DAY.get(
                    date.getMonthValue(),
                    date.getDayOfMonth(),
                    date.getDayOfWeek().getDisplayName(FULL, KOREA)
            ));
        }
    }

    public static void validateCrew(final Crew crew) {
        if (crew == null) {
            throw new IllegalArgumentException(NOT_FOUND_NICKNAME.get());
        }
    }

    private static void validateParsableDay(final int day) {
        try {
            DateTimes.now().withDayOfMonth(day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    private static void validateFutureDay(final int day) {
        if (DateTimes.now().getDayOfMonth() < day) {
            throw new IllegalArgumentException(NOT_MODIFY_DAY.get());
        }
    }
}
