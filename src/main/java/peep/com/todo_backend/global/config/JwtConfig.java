package peep.com.todo_backend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${custom.jwt.secretKey}")
    private String secretKey;

    @Value("${custom.jwt.expirationTime}")
    private long accessTokenExpirationTime;

    @Value("${custom.jwt.refresh}")
    private long refreshTokenExpirationTime;

    public String getSecretKey() {
        return secretKey;
    }

    public long getAccessTokenExpirationTime() {
        return accessTokenExpirationTime;
    }

    public long getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }
}