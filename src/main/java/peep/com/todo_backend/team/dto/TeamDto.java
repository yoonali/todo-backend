package peep.com.todo_backend.team.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class TeamDto {

    @Schema(description = "팀 이름", example = "PEEP")
    protected String name;

    @Schema(description = "프로젝트 이름", example = "TODO")
    protected String projectName;

    @Schema(description = "프로젝트 설명", example = "할 일을 관리하는 프로젝트")
    protected String description;

    @Schema(description = "프로젝트 시작일", example = "2025-03-20")
    protected LocalDate startDate;

    @Schema(description = "프로젝트 종료일", example = "2021-04-05")
    protected LocalDate endDate;
}
