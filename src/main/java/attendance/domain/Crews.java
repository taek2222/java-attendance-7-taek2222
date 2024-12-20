package attendance.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews() {
        this.crews = new ArrayList<>();
    }

    public void addCrew(Crew crew) {
        crews.add(crew);
    }

    public void updateCrewAttendance(String nickname, LocalDateTime dateTime) {
        Crew crew = findCrewByNickname(nickname);
        crew.updateAttendance(dateTime);
    }

    public boolean existsCrewByNickname(String nickname) {
        return findCrewByNickname(nickname) != null;
    }

    public Crew findCrewByNickname(String nickname) {
        return crews.stream()
                .filter(crew -> crew.isSameNickname(nickname))
                .findFirst()
                .orElse(null);
    }
}
