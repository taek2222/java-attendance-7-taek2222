package attendance.domain;

import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;

import attendance.domain.dto.ExpulsionRiskResponse;
import attendance.domain.dto.CrewResponse;
import java.util.Comparator;
import java.util.List;

public class Crews {

    private static final int PERCEPTION_TO_ABSENCE_RATIO = 3;

    private final List<Crew> crews;

    public Crews(final List<Crew> crews) {
        this.crews = crews;
    }

    // 제적 위험자 조회 반환
    public ExpulsionRiskResponse createExpulsionRiskResponse() {
        List<CrewResponse> crewResponses = createCrewResponses();
        crewResponses = sortCrewResponses(crewResponses);
        return new ExpulsionRiskResponse(crewResponses);
    }

    private List<CrewResponse> createCrewResponses() {
        return crews.stream()
                .map(Crew::createResponse)
                .toList();
    }

    // 제적 위험자 정렬
    private List<CrewResponse> sortCrewResponses(List<CrewResponse> responses) {
        return responses.stream()
                .sorted(Comparator.comparingInt((CrewResponse r) -> r.attendanceResult().absence() + r.attendanceResult().perception() / PERCEPTION_TO_ABSENCE_RATIO)
                        .thenComparingInt((CrewResponse r) -> r.attendanceResult().perception() % PERCEPTION_TO_ABSENCE_RATIO)
                        .reversed()
                        .thenComparing(CrewResponse::nickname))
                .toList();
    }

    public Crew findCrewByNickname(final String nickname) {
        return crews.stream()
                .filter(crew -> crew.isSameNickname(nickname))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_NICKNAME.get()));
    }
}
