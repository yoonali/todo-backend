package peep.com.todo_backend.team.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class TeamDto {
    protected String name;
    protected String projectName;
    protected String description;
    protected LocalDate startDate;
    protected LocalDate endDate;
}


