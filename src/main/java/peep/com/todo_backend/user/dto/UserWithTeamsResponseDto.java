package peep.com.todo_backend.user.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import peep.com.todo_backend.team.domain.Team;
import peep.com.todo_backend.team.dto.TeamResponseDto;
import peep.com.todo_backend.team.dto.dtoConverter.TeamDtoConverter;
import peep.com.todo_backend.user.domain.User;
import peep.com.todo_backend.user.dto.dtoConverter.UserDtoConverter;

@Getter
public class UserWithTeamsResponseDto {
    private UserResponseDto user;

    private List<TeamResponseDto> teams;

    public UserWithTeamsResponseDto(User user, List<Team> team) {
        this.user = UserDtoConverter.fromEntity(user);
        this.teams = team.stream()
                .map(TeamDtoConverter::toResponseDtoWithUser)
                .collect(Collectors.toList());
    }
}
