package peep.com.todo_backend.team.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiNotFoundError;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiSuccess;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerInternetServerError;
import peep.com.todo_backend.team.dto.TeamSaveDto;
import peep.com.todo_backend.team.service.TeamService;

@RestController
@RequestMapping("/api/v1/team")
@Tag(name = "Team API", description = "팀 프로젝트 관련 API")
@Slf4j
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @SwaggerApiSuccess(summary = "팀 프로젝트 생성", description = "신규 프로젝트를 생성합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PostMapping
    public ResponseEntity<?> saveTeam(@Valid @RequestBody TeamSaveDto dto) {
        return teamService.saveTeam(dto);
    }
}
