package peep.com.todo_backend.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import peep.com.todo_backend.team.domain.Team;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TeamResponseDto {
    private String name;
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String inviteLink;
    private LocalDateTime inviteExpiry;

    public TeamResponseDto(Team team) {
        this.name = team.getName();
        this.projectName = team.getProjectName();
        this.description = team.getDescription();
        this.startDate = team.getStartDate();
        this.endDate = team.getEndDate();
        this.inviteLink = team.getInviteLink();
        this.inviteExpiry = team.getInviteExpiry();
    }
}