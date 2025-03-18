package peep.com.todo_backend.user.dto;

import java.util.List;

import lombok.Getter;
import peep.com.todo_backend.team.dto.TeamResponseDto;
import peep.com.todo_backend.user.domain.User;

@Getter
public class UserWithTeamsResponseDto {
    private UserResponseDto user;

    private List<TeamResponseDto> team;

    public UserWithTeamsResponseDto(User user, List<TeamResponseDto> team) {
        this.user = new UserResponseDto(user);
        this.team = team;
    }
}
