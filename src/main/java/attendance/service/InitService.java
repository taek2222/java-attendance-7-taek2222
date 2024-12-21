package attendance.service;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.global.util.FileUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InitService {

    private static final String ATTENDANCES_FILE_NAME = "attendances.csv";
    private static final String NAME_DATETIME_DELIMITER = ",";
    private static final String PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public Crews initializeCrewsFromFile() {
        List<String> attendanceLines = FileUtil.readFile(ATTENDANCES_FILE_NAME);

        List<Crew> crews = new ArrayList<>();
        for (String line : attendanceLines) {
            String[] fields = line.split(NAME_DATETIME_DELIMITER);

            String nickname = fields[0];
            LocalDateTime dateTime = LocalDateTime.parse(fields[1], DATE_TIME_FORMATTER);

            addCrewIfNewCrew(crews, nickname);

            Crew crew = findByNickname(crews, nickname);
            crew.updateAttendance(dateTime);
        }
        return new Crews(crews);
    }

    private Crew findByNickname(List<Crew> crews, String nickname) {
        return crews.stream()
                .filter(crew -> crew.isSameNickname(nickname))
                .findFirst()
                .orElseThrow();
    }

    private void addCrewIfNewCrew(final List<Crew> crews, final String name) {
        if (!isNewCrew(crews, name)) {
            Crew crew = new Crew(name);
            crews.add(crew);
        }
    }

    private boolean isNewCrew(List<Crew> crews, final String name) {
        return crews.contains(new Crew(name));
    }
}
