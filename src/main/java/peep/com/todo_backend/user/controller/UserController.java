package peep.com.todo_backend.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiNotFoundError;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiSuccess;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerInternetServerError;
import peep.com.todo_backend.user.dto.UserSaveDto;
import peep.com.todo_backend.user.dto.UserUpdateDto;
import peep.com.todo_backend.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "유저 관련 API")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ** 회원 정보 등록
    @SwaggerApiSuccess(summary = "회원가입", description = "신규유저를 생성합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserSaveDto dto) {
        return userService.saveUser(dto);
    }

    // ** 회원 정보 조회
    @SwaggerApiSuccess(summary = "회원 정보 조회", description = "현재 로그인한 회원의 정보를 조회합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(
            @RequestParam(name = "userId") @Parameter(description = "조회할 사용자의 ID", required = true) Integer userId) {
        return userService.getUser(userId);
    }

    // ** 회원 정보 업데이트
    @SwaggerApiSuccess(summary = "회원 정보 수정", description = "현재 로그인한 회원의 정보를 수정합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(
            @Valid @RequestBody UserUpdateDto dto,
            @RequestParam(name = "userId") @Parameter(description = "수정할 사용자의 ID", required = true) Integer userId) {
        return userService.updateUser(dto, userId);
    }

    // ** 회원 삭제
    @SwaggerApiSuccess(summary = "회원 삭제", description = "현재 로그인한 회원을 삭제합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(
            @RequestParam(name = "userId") @Parameter(description = "삭제할 사용자의 ID", required = true) Integer userId) {
        return userService.deleteUser(userId);
    }

    // ** 회원 비밀번호 변경
    @SwaggerApiSuccess(summary = "비밀번호 변경", description = "현재 비밀번호를 확인 후 새 비밀번호로 변경합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PutMapping("/password")
    public ResponseEntity<?> changePassword(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "currentPassword") String currentPassword,
            @RequestParam(name = "newPassword") String newPassword) {
        return userService.changePassword(userId, currentPassword, newPassword);
    }
}
