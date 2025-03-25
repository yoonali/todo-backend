package peep.com.todo_backend.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveDto {
    @Schema(description = "사용자의 이메일 주소", example = "admin@gmail.com")
    private String email;

    @Schema(description = "사용자의 비밀번호", example = "qwer1234")
    private String password;
}
