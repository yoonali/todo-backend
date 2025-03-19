package peep.com.todo_backend.team.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamResponseDto extends TeamDto {
    private Integer id;
    private String teamToken;

    public TeamResponseDto(Integer id, String name, String projectName, String description, String teamToken,
            LocalDate startDate,
            LocalDate endDate) {
        super(name, projectName, description, startDate, endDate);
        this.id = id;
        this.teamToken = teamToken;
    }
}
