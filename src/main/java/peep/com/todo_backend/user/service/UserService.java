package peep.com.todo_backend.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import peep.com.todo_backend.global.Exception.BadRequestException;
import peep.com.todo_backend.global.dto.ResultDto;
import peep.com.todo_backend.global.enums.UserRole;
import peep.com.todo_backend.user.domain.User;
import peep.com.todo_backend.user.dto.UserSaveDto;
import peep.com.todo_backend.user.dto.UserUpdateDto;
import peep.com.todo_backend.user.repository.UserJpaRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;

    public ResponseEntity<?> saveUser(UserSaveDto dto) {
        // ** 유저네임 기반 유저 조회
        boolean existUserByPhoneNumber = userJpaRepository.existByUserEmail(dto.getEmail());

        // ** 중복 유저 존재
        if (existUserByPhoneNumber) {
            throw new BadRequestException("이미 사용 중인 아이디 입니다.");
        }

        // ** 랜덤 닉네임 생성
        String userNickname = getNickname();

        // ** 유저 생성
        User user = User.builder()
                .userType(UserRole.LOCAL)
                .email(dto.getEmail())
                .password(dto.getPassword())
                .token(dto.getToken())
                .nickname(userNickname) // 자동 생성된 닉네임
                .profile("") // 기본값
                .lastLoginDate(LocalDateTime.now()) // 기본값
                .isDeleted(false) // 기본값
                .build();

        userJpaRepository.save(user);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), "Create User SuccessFully"));
    }

    // ** 회원 정보 업데이트
    public ResponseEntity<?> updateUser(Integer userId, UserUpdateDto dto) {
        Optional<User> existingUser = userJpaRepository.findById(userId);
        if (existingUser.isEmpty()) {
            throw new BadRequestException("존재하지 않는 사용자입니다.");
        }

        User user = existingUser.get();
        user.setPassword(dto.getPassword());

        userJpaRepository.save(user);

        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), "Update User Successfully"));
    }

    private String getNickname() {
        String name = "user";

        Random random = new Random();
        String randomInt = String.valueOf(random.nextInt(999));
        return name + randomInt;
    }
}
