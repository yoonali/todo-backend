package peep.com.todo_backend.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peep.com.todo_backend.global.Exception.BadRequestException;
import peep.com.todo_backend.global.enums.UserRole;
import peep.com.todo_backend.team.domain.Team;
import peep.com.todo_backend.team.service.TeamService;
import peep.com.todo_backend.user.domain.User;
import peep.com.todo_backend.user.dto.UserSaveDto;
import peep.com.todo_backend.user.dto.UserUpdateDto;
import peep.com.todo_backend.user.dto.UserWithTeamsResponseDto;
import peep.com.todo_backend.user.repository.UserJpaRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    private final TeamService teamService;

    public void saveUser(UserSaveDto dto) {
        Optional<User> existingUser = userJpaRepository.findByEmail(dto.getEmail());

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            if (user.isDeleted()) {
                // ** Soft Delete 상태의 데이터를 완전 삭제
                userJpaRepository.delete(user);
                log.info("Soft Delete 상태의 사용자 데이터를 완전히 삭제하였습니다: {}", user.getEmail());
            } else {
                throw new BadRequestException("이미 사용 중인 이메일입니다.");
            }
        }

        // 새로운 사용자 생성
        User newUser = User.builder()
                .userType(UserRole.LOCAL)
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .token("")
                .nickname(getNickname()) // 자동 생성된 닉네임
                .profile("") // 기본값
                .lastLoginDate(LocalDateTime.now()) // 기본값
                .isDeleted(false)
                .build();

        userJpaRepository.save(newUser);
    }

    // ** 회원 정보 조회
    public UserWithTeamsResponseDto getUser(Integer userId) {
        User user = userJpaRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new BadRequestException("존재하지 않거나 삭제된 사용자입니다."));

        List<Team> team = teamService.findPersonalTeamList(userId);

        return new UserWithTeamsResponseDto(user, team);
    }

    // ** 회원 정보 업데이트 (Soft Delete 적용)
    public void updateUser(UserUpdateDto dto, Integer userId) {
        User user = userJpaRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new BadRequestException("존재하지 않거나 삭제된 사용자입니다."));

        user.setNickname(dto.getNickName());

        userJpaRepository.save(user);
    }

    // ** 회원 정보 삭제 (Soft Delete 적용)
    public void deleteUser(Integer userId) {
        User user = userJpaRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new BadRequestException("존재하지 않거나 이미 삭제된 사용자입니다."));

        user.setDeleted(true);

        userJpaRepository.save(user);
    }

    // ** 회원 비밀번호 변경 (Soft Delete 적용)
    public void changePassword(Integer userId, String currentPassword, String newPassword) {
        User user = userJpaRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new BadRequestException("존재하지 않거나 삭제된 사용자입니다."));

        // 현재 비밀번호 검증
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BadRequestException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 새 비밀번호 암호화 후 저장
        user.setPassword(passwordEncoder.encode(newPassword));

        userJpaRepository.save(user);
    }

    private String getNickname() {
        String name = "user";

        Random random = new Random();
        String randomInt = String.valueOf(random.nextInt(999));
        return name + randomInt;
    }
}
