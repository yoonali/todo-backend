package peep.com.todo_backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peep.com.todo_backend.user.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Integer>, UserJpaCustomRepository {
}
