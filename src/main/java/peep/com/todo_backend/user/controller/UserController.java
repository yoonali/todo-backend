package peep.com.todo_backend.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import peep.com.todo_backend.global.Response.ApiSuccessResponse;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiNotFoundError;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiSuccess;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerInternetServerError;
import peep.com.todo_backend.global.dto.ResultDto;
import peep.com.todo_backend.user.dto.UserSaveDto;
import peep.com.todo_backend.user.dto.UserUpdateDto;
import peep.com.todo_backend.user.dto.UserWithTeamsResponseDto;
import peep.com.todo_backend.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "유저 관련 API")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ** 회원 정보 등록
    @SwaggerApiSuccess(summary = "회원가입", description = "회원 정보를 등록합니다.", value = ApiSuccessResponse.USER_SAVE)
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PostMapping
    public ResponseEntity<ResultDto<?>> saveUser(@Valid @RequestBody UserSaveDto dto) {
        userService.saveUser(dto);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "SUCCESS", "회원가입 성공"));
    }

    // ** 회원 정보 조회
    @SwaggerApiSuccess(summary = "회원 정보 조회", description = "현재 로그인한 회원의 정보를 조회합니다.", value = ApiSuccessResponse.USER_GET)
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(
            @AuthenticationPrincipal Integer userId) {
        UserWithTeamsResponseDto responseDto = userService.getUser(userId);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "SUCCESS", "회원 정보 조회 성공", responseDto));
    }

    // ** 회원 정보 업데이트
    @SwaggerApiSuccess(summary = "회원 정보 수정", description = "현재 로그인한 회원의 정보를 수정합니다.", value = ApiSuccessResponse.USER_UPDATE)
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PutMapping("/updateUser")
    public ResponseEntity<ResultDto<?>> updateUser(
            @Valid @RequestBody UserUpdateDto dto,
            @AuthenticationPrincipal Integer userId) {
        userService.updateUser(dto, userId);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "SUCCESS", "회원 정보 수정 성공"));
    }

    // ** 회원 삭제
    @SwaggerApiSuccess(summary = "회원 삭제", description = "현재 로그인한 회원을 삭제합니다.", value = ApiSuccessResponse.USER_DELETE)
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal Integer userId) {
        userService.deleteUser(userId);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "SUCCESS", "회원 삭제 성공"));
    }

    // ** 회원 비밀번호 변경
    @SwaggerApiSuccess(summary = "비밀번호 변경", description = "현재 비밀번호를 확인 후 새 비밀번호로 변경합니다.", value = ApiSuccessResponse.USER_PASSWORD_UPDATE)
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PutMapping("/password")
    public ResponseEntity<?> changePassword(
            @AuthenticationPrincipal Integer userId,
            @RequestParam(name = "currentPassword") String currentPassword,
            @RequestParam(name = "newPassword") String newPassword) {
        userService.changePassword(userId, currentPassword, newPassword);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "SUCCESS", "비밀번호 변경 성공"));
    }
}
