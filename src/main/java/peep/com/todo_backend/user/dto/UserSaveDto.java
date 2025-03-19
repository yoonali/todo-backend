package peep.com.todo_backend.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveDto {
    private String email;
    private String password;
}
