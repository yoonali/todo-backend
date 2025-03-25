package peep.com.todo_backend.global.customAnnotation.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Operation()
@ApiResponse(responseCode = "200", description = "", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "200 성공 예시", value = "")))
public @interface SwaggerApiSuccess {
        String summary() default "";

        String description();

        String value();
}
