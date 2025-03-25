package peep.com.todo_backend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequestDto {

    @Schema(description = "refresh token 값", example = "")
    @NotBlank(message = "Refresh Token은 필수 입력 값입니다.")
    private String refreshToken;
}
