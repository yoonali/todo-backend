package peep.com.todo_backend.team.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import peep.com.todo_backend.team.domain.Team;

public interface TeamJpaRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findByTeamToken(String teamToken);
}
