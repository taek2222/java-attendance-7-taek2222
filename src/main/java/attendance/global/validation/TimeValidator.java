package attendance.global.validation;

import static attendance.global.constant.ErrorMessage.CAMPUS_OPERATING_TIME;
import static attendance.global.constant.ErrorMessage.INVALID_TIME_INPUT;

import attendance.domain.time.CampusTime;
import java.time.DateTimeException;
import java.time.LocalTime;

public class TimeValidator {

    public static void validateTimeParse(final String time) {
        try {
            LocalTime.parse(time);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_TIME_INPUT.get());
        }
    }

    public static void validateCampusOperateTime(final LocalTime time) {
        if (!CampusTime.isCampusOperateTime(time)) {
            throw new IllegalArgumentException(CAMPUS_OPERATING_TIME.get());
        }
    }
}
