package peep.com.todo_backend.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTimeEntity {
    @Comment("생성 시간")
    @Column(nullable = false, name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Comment("수정 시간")
    @Column(nullable = false, name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
