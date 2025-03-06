package peep.com.todo_backend.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peep.com.todo_backend.global.utils.JwtUtil;
import peep.com.todo_backend.user.domain.User;
import peep.com.todo_backend.user.repository.UserJpaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public ResponseEntity<Map<String, String>> login(String email, String password) {
        User user = userJpaRepository.findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Generate Access and Refresh Tokens
        String accessToken = JwtUtil.createAccessToken(user.getUserId(), user.getEmail());
        String refreshToken = JwtUtil.createRefreshToken(user.getUserId(), user.getEmail());

        // Save Refresh Token to Redis (Expiration time 설정)
        redisTemplate.opsForValue().set(user.getEmail(), refreshToken,
                JwtUtil.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        // Create response with both tokens
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return ResponseEntity.ok(tokens);
    }

    public ResponseEntity<String> refreshAccessToken(String refreshToken) {
        if (!JwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        Integer userId = JwtUtil.getUserId(refreshToken);
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Redis에서 Refresh Token 검증
        String redisToken = redisTemplate.opsForValue().get(user.getEmail());
        if (redisToken == null || !redisToken.equals(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        String newAccessToken = JwtUtil.createAccessToken(user.getUserId(), user.getEmail());
        return ResponseEntity.ok(newAccessToken);
    }

}
