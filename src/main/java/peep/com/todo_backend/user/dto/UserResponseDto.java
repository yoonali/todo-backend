package peep.com.todo_backend.user.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import peep.com.todo_backend.user.domain.User;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String nickname;
    private String profile;
    private String email;
    private String token;
    private LocalDateTime lastLoginDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserResponseDto(User user) {
        this.id = user.getUserId();
        this.nickname = user.getNickname();
        this.profile = user.getProfile();
        this.email = user.getEmail();
        this.token = user.getToken();
        this.lastLoginDate = user.getLastLoginDate();
        this.createdDate = user.getCreatedDate();
        this.modifiedDate = user.getModifiedDate();
    }
}
