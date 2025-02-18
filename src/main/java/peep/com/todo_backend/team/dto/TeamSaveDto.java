package peep.com.todo_backend.team.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamSaveDto {
    @Schema(description = "팀 이름", example = "PEEP")
    @NotNull(message = "팀 이름은 필수값입니다.")
    private String name;

    @Schema(description = "프로젝트 이름", example = "PEEP")
    @NotNull(message = "프로젝트 이름은 필수값입니다.")
    private String projectName;

    @Schema(description = "설명", example = "김효진과 임유나와 최수진과 이예빈이 함께 베포하기 위해서 만드는 앱")
    private String description;

    @Schema(description = "시작 날짜", example = "2025-02-18")
    private LocalDate startDate;

    @Schema(description = "종료 날짜", example = "2025-05-18")
    private LocalDate endDate;
}
