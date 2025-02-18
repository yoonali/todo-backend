package peep.com.todo_backend.global.customAnnotation.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation()
@ApiResponses(value =
    @ApiResponse(
            responseCode = "500",
            description = "서버 오류가 발생했습니다..",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Error.class)))
)
public @interface SwaggerInternetServerError {
}
