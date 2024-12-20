package attendance.global.validation;

import static attendance.global.constant.ErrorMessage.INVALID_INPUT;
import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;
import static attendance.global.constant.ErrorMessage.NOT_SCHOOL_DAY;
import static java.time.format.TextStyle.FULL;
import static java.util.Locale.KOREA;

import attendance.domain.Crew;
import attendance.domain.Holiday;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class AttendanceValidator {

    public static void validateDay(final int day) {
        try {
            DateTimes.now().withDayOfMonth(day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_INPUT.get());
        }
    }

    public static void validateSchoolDay(final LocalDateTime dateTime) {
        if (Holiday.isHoliday(dateTime)) {
            throw new IllegalArgumentException(NOT_SCHOOL_DAY.get(
                    dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dateTime.getDayOfWeek().getDisplayName(FULL, KOREA)
            ));
        }
    }

    public static void validateCrew(final Crew crew) {
        if (crew == null) {
            throw new IllegalArgumentException(NOT_FOUND_NICKNAME.get());
        }
    }
}
