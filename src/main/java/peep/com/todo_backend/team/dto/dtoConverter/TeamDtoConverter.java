package peep.com.todo_backend.team.dto.dtoConverter;

import peep.com.todo_backend.team.domain.Team;
import peep.com.todo_backend.team.dto.TeamResponseDto;

public class TeamDtoConverter {

    private TeamDtoConverter() {
    }

    public static TeamResponseDto toResponseDto(Team team) {
        if (team == null) {
            return null;
        }
        return new TeamResponseDto(
                team.getTeamId(),
                team.getName(),
                team.getProjectName(),
                team.getDescription(),
                null,
                team.getStartDate(),
                team.getEndDate());
    }

    public static TeamResponseDto toResponseDtoWithUser(Team team) {
        if (team == null) {
            return null;
        }
        return new TeamResponseDto(
                team.getTeamId(),
                team.getName(),
                team.getProjectName(),
                team.getDescription(),
                team.getTeamToken(),
                team.getStartDate(),
                team.getEndDate());
    }

}
