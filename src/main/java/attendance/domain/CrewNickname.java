package attendance.domain;

import static attendance.global.constant.ErrorMessage.INVALID_NAME;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public enum CrewNickname {
    빙봉("빙봉"),
    이든("이든"),
    쿠키("쿠키"),
    빙티("빙티"),
    짱수("짱수")
    ;

    private final String nickname;

    CrewNickname(final String nickname) {
        this.nickname = nickname;
    }

    public static String findByName(String name) {
        CrewNickname crewNickname = Arrays.stream(values())
                .filter(crew -> crew.nickname.equals(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException(INVALID_NAME.get()));
        return crewNickname.nickname;
    }

    public static List<Crew> generateCrewDefault() {
        return Arrays.stream(values())
                .map(crew-> new Crew(crew.nickname, AttendanceType.ABSENCE, LocalTime.of(0, 0, 0)))
                .toList();
    }
}
