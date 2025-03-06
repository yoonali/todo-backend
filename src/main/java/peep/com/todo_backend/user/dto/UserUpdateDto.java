package peep.com.todo_backend.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {
    @Schema(description = "사용자 닉네임", example = "user12345")
    @NotNull(message = "닉네임은 필수 입력 값입니다.")
    private String nickName;
}
