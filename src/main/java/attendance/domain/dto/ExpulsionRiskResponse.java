package attendance.domain.dto;

import java.util.List;

public record ExpulsionRiskResponse(
        List<RecordResponse> expulsionRisks
) {
}
