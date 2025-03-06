package peep.com.todo_backend.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import peep.com.todo_backend.user.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Integer>, UserJpaCustomRepository {

    // JPA 메서드 쿼리를 통해 자동으로 메서드 구현
    boolean existsByEmail(String email);

    // 삭제되지 않은 사용자만 조회
    Optional<User> findByUserIdAndIsDeletedFalse(Integer userId);

    // 삭제 여부와 상관없이 모든 사용자 조회
    Optional<User> findByEmail(String email);

    // 로그인 시 이메일로 조회 (삭제되지 않은 사용자만)
    Optional<User> findByEmailAndIsDeletedFalse(String email);

    // 30일 이상 지난 Soft Delete 사용자 조회
    List<User> findByIsDeletedTrueAndModifiedDateBefore(LocalDateTime dateTime);

}
