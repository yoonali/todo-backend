package peep.com.todo_backend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    @Schema(description = "사용자의 이메일 주소", example = "admin@gmail.com")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Schema(description = "사용자의 비밀번호", example = "qwer1234")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
