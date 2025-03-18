package peep.com.todo_backend.user.repository.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import peep.com.todo_backend.user.domain.User;

@Repository
public interface FriendJpaRepository extends JpaRepository<User, Integer> {

}
