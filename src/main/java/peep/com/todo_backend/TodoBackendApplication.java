package peep.com.todo_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import peep.com.todo_backend.global.config.JwtConfig;
import peep.com.todo_backend.global.utils.JwtUtil;

@SpringBootApplication
@EnableJpaAuditing
public class TodoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner initJwt(JwtConfig jwtConfig) {
		return args -> {
			JwtUtil.init(
					jwtConfig.getSecretKey(),
					jwtConfig.getAccessTokenExpirationTime(),
					jwtConfig.getRefreshTokenExpirationTime());
		};
	}

}
