package peep.com.todo_backend.user.repository;

public interface UserJpaCustomRepository {
    boolean existByUserEmail(String email);
}
