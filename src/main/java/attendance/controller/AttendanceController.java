package attendance.controller;


import attendance.domain.Attendance;
import attendance.domain.Attendances;
import attendance.domain.Crew;
import attendance.domain.CrewNickname;
import attendance.domain.DayOfTheWeek;
import attendance.domain.dto.AttendanceResponse;
import attendance.global.util.AttendancesParser;
import attendance.global.util.CrewParser;
import attendance.global.util.DateTimeParser;
import attendance.global.util.FileUtil;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Attendances attendances = initAttendances();
        updateFileAttendances(attendances);

        outputView.printChoiceFunction();
        String function = inputView.readChoiceFunction();

        if (function.equals("1")) {
            LocalDateTime now = DateTimes.now();
            DayOfTheWeek.findByDate(now);
            String nickname = inputView.readNickname();
            CrewNickname.findByName(nickname);
            String startTime = inputView.readSchoolStartTime();

            LocalDateTime dateTime = DateTimeParser.parseDateTime(LocalDate.from(now), startTime);
            Crew crew = CrewParser.parseCrew(nickname, dateTime);
            attendances.updateNewAttendance(crew, dateTime);
            Attendance attendance = attendances.findAttendanceByDate(dateTime);
            outputView.printCrewAttendanceStatus(attendance.createResponseByCrew(crew));
        }

        if (function.equals("2")) {
            String nickname = inputView.readNickname();
            int day = inputView.readDay();
            String time = inputView.readChangeTime();

            LocalDate date = LocalDate.of(2024, 12, day);
            LocalDateTime dateTime = DateTimeParser.parseDateTime(date, time);

            Crew crew = CrewParser.parseCrew(nickname, dateTime);
            Attendance attendance = attendances.findAttendanceByDate(dateTime);

            Crew oldCrew = attendance.findCrewByCrew(crew);
            AttendanceResponse oldCrewResponse = attendance.createResponseByCrew(oldCrew);
            attendance.updateCrew(crew);

            AttendanceResponse newCrewResponse = attendance.createResponseByCrew(crew);
            outputView.printCrewModified(oldCrewResponse, newCrewResponse);
        }
    }

    private void updateFileAttendances(final Attendances attendances) {
        List<String> fileAttendances = FileUtil.readFile("attendances.csv");
        AttendancesParser.parseAndUpdateAttendances(attendances, fileAttendances);
    }


    private Attendances initAttendances() {
        List<Attendance> attendances = new ArrayList<>();
        LocalDateTime now = DateTimes.now().plusDays(1);
        LocalDateTime start = LocalDateTime.of(2024, 12, 1, 0, 0, 0);
        while (!start.toLocalDate().isEqual(now.toLocalDate())) {
            Attendance attendance = new Attendance(start, CrewNickname.generateCrewDefault());
            attendances.add(attendance);
            start = start.plusDays(1);
        }
        return new Attendances(attendances);
    }
}
