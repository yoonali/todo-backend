package peep.com.todo_backend.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequestDto {

    @NotBlank(message = "Refresh Token은 필수 입력 값입니다.")
    private String refreshToken;
}
