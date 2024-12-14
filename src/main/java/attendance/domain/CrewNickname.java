package attendance.domain;

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

    public CrewNickname findByName(String name) {
        return Arrays.stream(values())
                .filter(crew -> crew.nickname.equals(name))
                .findFirst()
                .orElseThrow(); // todo : 찾지 못한 에러
    }

    public static List<Crew> generateCrewDefault() {
        return Arrays.stream(values())
                .map(crew-> new Crew(crew.nickname, AttendanceType.ABSENCE))
                .toList();
    }
}
