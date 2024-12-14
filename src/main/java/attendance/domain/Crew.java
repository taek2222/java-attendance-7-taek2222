package attendance.domain;

import attendance.domain.dto.CrewResponse;
import java.time.LocalTime;
import java.util.Objects;

public class Crew {

    private final String nickname;
    private AttendanceType attendance;
    private LocalTime time;

    public Crew(final String nickname, final AttendanceType attendance, final LocalTime time) {
        this.nickname = nickname;
        this.attendance = attendance;
        this.time = time;
    }

    public void updateCrew(Crew newCrew) {
        this.attendance = newCrew.attendance;
        this.time = newCrew.time;
    }

    public CrewResponse createResponse() {
        return new CrewResponse(
                nickname,
                attendance.getType(),
                time
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Crew that = (Crew) obj;

        return Objects.equals(this.nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}
