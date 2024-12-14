package attendance.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Crew {

    private final String nickname;
    private AttendanceType attendance;

    public Crew(final String nickname, final AttendanceType attendance) {
        this.nickname = nickname;
        this.attendance = attendance;
    }

    public void updateCrew(Crew newCrew) {
        this.attendance = newCrew.attendance;
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
