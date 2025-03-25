package peep.com.todo_backend.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import peep.com.todo_backend.global.customAnnotation.swagger.SwaggerApiSuccess;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.examples.Example;

import java.lang.reflect.Method;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                                .components(new Components()
                                                .addSecuritySchemes("JWT", createAPIKeyScheme()))
                                .info(new Info()
                                                .title("Todo Backend API")
                                                .description("API for Todo Backend")
                                                .version("v1.0.0"));
        }

        private SecurityScheme createAPIKeyScheme() {
                return new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer");
        }

        @Bean
        public OperationCustomizer customizeOperation() {
                return (operation, handlerMethod) -> {
                        Method method = handlerMethod.getMethod(); // Get the Method object
                        SwaggerApiSuccess annotation = method.getAnnotation(SwaggerApiSuccess.class);
                        if (annotation != null) {
                                ApiResponse apiResponse = operation.getResponses().get("200");
                                if (apiResponse != null) {
                                        MediaType content = apiResponse.getContent().get("application/json");
                                        if (content != null) {
                                                Example example = new Example();
                                                example.setValue(annotation.value());
                                                content.addExamples("200 성공 예시", example);
                                        }
                                }
                        }
                        return operation;
                };
        }
}