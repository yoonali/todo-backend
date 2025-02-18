package peep.com.todo_backend.team.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import peep.com.todo_backend.global.domain.BaseTimeEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_TEAM")
public class Team extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @Comment("팀 이름")
    @Column(length = 50, unique = true, nullable = false, name = "name")
    private String name;

    @Comment("프로젝트 이름")
    @Column(length = 50, nullable = false, name = "project_name")
    private String projectName;

    @Comment("설명")
    @Column(length = 150, nullable = true, name = "description")
    private String description;

    @Comment("시작 날짜")
    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Comment("종료 날짜")
    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;
}
