package peep.com.todo_backend.team.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import peep.com.todo_backend.global.enums.TeamUserRole;
import peep.com.todo_backend.team.domain.Team;
import peep.com.todo_backend.team.domain.TeamUser;
import peep.com.todo_backend.user.domain.User;

@Repository
public interface TeamUserJpaRepository extends JpaRepository<TeamUser, Integer> {
    boolean existsByTeamAndUserAndTeamUserRole(Team team, User user, TeamUserRole teamUserRole);

    @Query("SELECT DISTINCT t FROM Team t " +
            "JOIN TeamUser tu ON t.teamId = tu.team.teamId " +
            "WHERE tu.user.userId = :userId AND tu.teamUserRole = :teamUserRole")
    List<Team> findTeamsByUserIdAndRole(@Param("userId") Integer userId,
            @Param("teamUserRole") TeamUserRole teamUserRole);
}
