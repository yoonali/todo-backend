package peep.com.todo_backend.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static Key secretKey; // Key 타입으로 변경
    private static long accessTokenExpirationTime;
    private static long refreshTokenExpirationTime;

    // 초기화 메서드 (외부에서 호출 필요)
    public static void init(String secretKey, long accessTokenExpirationTime, long refreshTokenExpirationTime) {
        JwtUtil.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes()); // Key 생성 시 안전한 방식 사용
        JwtUtil.accessTokenExpirationTime = accessTokenExpirationTime;
        JwtUtil.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    // JWT 토큰 생성 (Access Token)
    public static String createAccessToken(Integer userId, String email) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256) // 변경된 signWith 사용법
                .compact();
    }

    // JWT 토큰 생성 (Refresh Token)
    public static String createRefreshToken(Integer userId, String email) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰에서 사용자 ID 추출
    public static Integer getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("userId", Integer.class);
    }

    // JWT 토큰 검증
    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 📌 Claims 추출 메서드 구현 (최신 메서드 사용)
    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // Key 인터페이스 사용
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Refresh Token 만료 시간 반환 메서드
    public static long getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }
}
