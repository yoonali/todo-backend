package peep.com.todo_backend.team.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamSaveDto extends TeamDto {
    public TeamSaveDto(String name, String projectName, String description, LocalDate startDate, LocalDate endDate) {
        super(name, projectName, description, startDate, endDate);
    }
}
