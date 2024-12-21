package attendance.domain;

import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;

import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews(final List<Crew> crews) {
        this.crews = crews;
    }

    public Crew getCrewByNickname(final String nickname) {
        Crew crew = findCrewByNickname(nickname);
        validateCrew(crew);
        return crew;
    }

    private Crew findCrewByNickname(String nickname) {
        return crews.stream()
                .filter(crew -> crew.isSameNickname(nickname))
                .findFirst()
                .orElse(null);
    }

    private static void validateCrew(final Crew crew) {
        if (crew == null) {
            throw new IllegalArgumentException(NOT_FOUND_NICKNAME.get());
        }
    }
}
