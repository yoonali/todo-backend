package peep.com.todo_backend.team.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import peep.com.todo_backend.global.dto.ResultDto;
import peep.com.todo_backend.team.domain.Team;
import peep.com.todo_backend.team.dto.TeamSaveDto;
import peep.com.todo_backend.team.repository.TeamJpaRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class TeamService {

    private final TeamJpaRepository teamJpaRepository;

    public ResponseEntity<?> saveTeam(TeamSaveDto dto) {
        Team team = Team.builder()
                .name(dto.getName())
                .projectName(dto.getProjectName())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        teamJpaRepository.save(team);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), "Create Team SuccessFully"));
    }
}
