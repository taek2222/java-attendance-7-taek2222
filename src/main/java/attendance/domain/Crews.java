package attendance.domain;

import static attendance.global.constant.ErrorMessage.NOT_FOUND_NICKNAME;

import attendance.domain.dto.ExpulsionRiskResponse;
import attendance.domain.dto.RecordResponse;
import java.util.Comparator;
import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews(final List<Crew> crews) {
        this.crews = crews;
    }

    public ExpulsionRiskResponse createExpulsionRiskResponse() {
        List<RecordResponse> expulsionRisks = createRecordResponses();
        expulsionRisks = sortRecordResponses(expulsionRisks);
        return new ExpulsionRiskResponse(expulsionRisks);
    }

    private List<RecordResponse> createRecordResponses() {
        return crews.stream()
                .map(Crew::createResponse)
                .toList();
    }

    private List<RecordResponse> sortRecordResponses(List<RecordResponse> responses) {
        return responses.stream()
                .sorted(Comparator.comparingInt((RecordResponse r) -> r.attendanceResult().absence() + r.attendanceResult().perception() / 3)
                        .thenComparingInt((RecordResponse r) -> r.attendanceResult().perception() % 3)
                        .reversed()
                        .thenComparing(RecordResponse::nickname))
                .toList();
    }

    public Crew findCrewByNickname(final String nickname) {
        return crews.stream()
                .filter(crew -> crew.isSameNickname(nickname))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_NICKNAME.get()));
    }
}
