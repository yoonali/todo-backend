package peep.com.todo_backend.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private final  UserService userService;

    @SwaggerApiSuccess(summary = "회원가입", description = "신규유저를 생성합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserSaveDto dto) {
        return userService.saveUser(dto);
    }



    // ** 회원 정보 업데이트
    @SwaggerApiSuccess(summary = "회원 정보 수정", description = "기존 회원의 정보를 수정합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserUpdateDto dto) {
        return userService.updateUser(userId, dto);
    }

    /*// ** 회원 삭제
    @SwaggerApiSuccess(summary = "회원 삭제", description = "기존 회원을 삭제합니다.")
    @SwaggerApiNotFoundError
    @SwaggerInternetServerError
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }*/
}
