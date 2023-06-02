package com.example.responsibilitysegregation10buisnessserver.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// /login 경로 이외의 경로를 처리하는 필터
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Value("${jwt.signing.key}")
  private String singingkey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String jwt = request.getHeader("Authorization");

    SecretKey key = Keys.hmacShaKeyFor(singingkey.getBytes(StandardCharsets.UTF_8));

    // JWs -> JSON Web Token Signed
    Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

    String username = String.valueOf(claims.get("username"));

    // 권한 등록
    GrantedAuthority a = new SimpleGrantedAuthority("user");

    // 인증 완료
    var auth = new UsernamePasswordAuthentication(username, null, List.of(a));
    SecurityContextHolder.getContext()
                         .setAuthentication(auth);

    // 다음 필터 진행
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath()
                  .equals("/login");
  }
}
