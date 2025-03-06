package peep.com.todo_backend.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import peep.com.todo_backend.user.domain.User;
import peep.com.todo_backend.user.repository.UserJpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCleanupService {

    private final UserJpaRepository userJpaRepository;

    // 매일 00시에 30일 지난 유저 삭제
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeExpiredSoftDeletedUsers() {
        LocalDateTime expirationDate = LocalDateTime.now().minusDays(30);
        List<User> usersToDelete = userJpaRepository.findByIsDeletedTrueAndModifiedDateBefore(expirationDate);

        if (!usersToDelete.isEmpty()) {
            userJpaRepository.deleteAll(usersToDelete);
            log.info("Soft Delete 후 30일 경과 사용자 {}명 삭제 완료.", usersToDelete.size());
        } else {
            log.info("삭제할 만료된 사용자가 없습니다.");
        }
    }

}
