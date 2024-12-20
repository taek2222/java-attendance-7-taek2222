package attendance.domain.dto;

public record ResultResponse(
        int attendance,
        int perception,
        int absence,
        String weeding
) {
}
