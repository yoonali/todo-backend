package peep.com.todo_backend.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {
    @Schema(description = "사용자의 닉네임", example = "유나낭")
    private String nickName;
}
