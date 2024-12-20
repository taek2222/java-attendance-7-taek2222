package attendance.service;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.global.util.FileUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InitService {

    private static final String PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final String NAME_DATETIME_DELIMITER = ",";
    private static final String ATTENDANCES_FILE_NAME = "attendances.csv";

    public Crews initializeCrewsFromFile() {
        List<String> attendanceLines = FileUtil.readFile(ATTENDANCES_FILE_NAME);
        Crews crews = new Crews();

        for (String line : attendanceLines) {
            String[] fields = line.split(NAME_DATETIME_DELIMITER);

            String name = fields[0];
            LocalDateTime dateTime = LocalDateTime.parse(fields[1], DATE_TIME_FORMATTER);

            addCrewIfNewCrew(crews, name);
            crews.updateCrewAttendance(name, dateTime);
        }
        return crews;
    }

    private void addCrewIfNewCrew(final Crews crews, final String name) {
        if (isNewCrew(crews, name)) {
            Crew crew = new Crew(name);
            crews.addCrew(crew);
        }
    }

    private boolean isNewCrew(final Crews crews, final String name) {
        return !crews.existsCrewByNickname(name);
    }
}
