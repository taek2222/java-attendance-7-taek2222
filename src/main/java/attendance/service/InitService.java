package attendance.service;

import static attendance.global.constant.ErrorMessage.SERVER_ERROR;

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
        attendanceLines.stream()
                .map(this::parseLine)
                .forEach(fields -> processAttendanceLine(crews, fields));

        return new Crews(crews);
    }

    private String[] parseLine(String line) {
        return line.split(NAME_DATETIME_DELIMITER);
    }

    // CSV 한 줄씩 처리 과정
    private void processAttendanceLine(List<Crew> crews, String[] fields) {
        String nickname = fields[0];
        addCrewIfNotExists(crews, nickname);
        Crew crew = findCrewByNickname(crews, nickname);

        LocalDateTime dateTime = LocalDateTime.parse(fields[1], DATE_TIME_FORMATTER);
        crew.updateAttendance(dateTime);
    }

    // 새로운 크루 추가 (이미 생성된 동일한 닉네임을 가진 크루가 없는 경우)
    private void addCrewIfNotExists(final List<Crew> crews, final String name) {
        if (!containsCrew(crews, name)) {
            Crew crew = new Crew(name);
            crews.add(crew);
        }
    }

    private boolean containsCrew(final List<Crew> crews, final String name) {
        return crews.contains(new Crew(name));
    }

    private Crew findCrewByNickname(List<Crew> crews, String nickname) {
        return crews.stream()
                .filter(crew -> crew.isSameNickname(nickname))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(SERVER_ERROR.get()));
    }
}
