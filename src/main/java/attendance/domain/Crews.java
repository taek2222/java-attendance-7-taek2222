package attendance.domain;

import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;

import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews(final List<Crew> crews) {
        this.crews = crews;
    }

    public Crew findCrewByNickname(final String nickname) {
        return crews.stream()
                .filter(crew -> crew.isSameNickname(nickname))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_NICKNAME.get()));
    }
}
