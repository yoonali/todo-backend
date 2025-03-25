package peep.com.todo_backend.global.filter;

import io.jsonwebtoken.Claims;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import peep.com.todo_backend.global.utils.JwtUtil;
import peep.com.todo_backend.user.domain.User;
import peep.com.todo_backend.user.repository.UserJpaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserJpaRepository userJpaRepository;

    public JwtAuthenticationFilter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);
        if (token != null && JwtUtil.validateToken(token)) {
            Claims claims = JwtUtil.getClaims(token);
            Integer userId = claims.get("userId", Integer.class);

            User user = userJpaRepository.findByUserId(userId)
                    .orElse(null);

            // ❗ 삭제되었거나 존재하지 않는 유저면 401 Unauthorized
            if (user == null || user.isDeleted()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"유효하지 않거나 삭제된 사용자입니다.\"}");
                return;
            }

            PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userId, null,
                    null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
