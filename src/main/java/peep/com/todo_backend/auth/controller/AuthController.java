package peep.com.todo_backend.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import peep.com.todo_backend.auth.dto.LoginRequestDto;
import peep.com.todo_backend.auth.dto.RefreshTokenRequestDto;
import peep.com.todo_backend.auth.service.AuthService;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiNotFoundError;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiSuccess;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerInternetServerError;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth API", description = "인증 관련 API")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ** 로그인
    @SwaggerApiSuccess(summary = "로그인", description = "사용자 로그인을 수행합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto.getEmail(), dto.getPassword()));
    }

    // ** Access Token 갱신
    @SwaggerApiSuccess(summary = "Access Token 갱신", description = "Refresh Token을 사용해 새로운 Access Token을 발급받습니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@Valid @RequestBody RefreshTokenRequestDto dto) {
        return ResponseEntity.ok(authService.refreshAccessToken(dto.getRefreshToken()));
    }

}
