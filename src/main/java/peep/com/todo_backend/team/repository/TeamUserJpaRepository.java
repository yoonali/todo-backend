package peep.com.todo_backend.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import peep.com.todo_backend.global.enums.TeamUserRole;
import peep.com.todo_backend.team.domain.Team;
import peep.com.todo_backend.team.domain.TeamUser;
import peep.com.todo_backend.user.domain.User;

@Repository
public interface TeamUserJpaRepository extends JpaRepository<TeamUser, Integer> {
    boolean existsByTeamAndUserAndTeamUserRole(Team team, User user, TeamUserRole teamUserRole);
}
