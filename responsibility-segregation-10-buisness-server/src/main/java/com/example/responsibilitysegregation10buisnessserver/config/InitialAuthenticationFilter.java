package com.example.responsibilitysegregation10buisnessserver.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class InitialAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationManager manager;

  public InitialAuthenticationFilter(AuthenticationManager manager) {
    this.manager = manager;
  }

  @Value("${jwt.signing.key}")
  private String signingKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String username = request.getHeader("username");
    String password = request.getHeader("password");
    String code = request.getHeader("code");

    log.info("username : " + username + ", password : " + password + ", code : " + code);

    if (code == null) { // code가 없으면 1단계 인증

      // AuthenticationProvider의 매개변수로 전달하기 위해 만듦.
      Authentication a = new UsernamePasswordAuthentication(username, password);
      manager.authenticate(a); //인증관리자가 authentication를 지원하는 인증공급자를 찾아서 authenticate 책임을 넘김
    } else {

      // AuthenticationProvider의 매개변수로 전달하기 위해 만듦.
      Authentication a = new OtpAuthentication(username, code);

      a = manager.authenticate(a);

      // 비밀키로 HMAC-SHA 알고리즘을 이용한 해쉬값
      SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

      String jwt = Jwts.builder()
                       .setClaims(Map.of("username", username))
                       .signWith(key)
                       .compact();

      // 클라이언트에게 jwt를 넘겨줌
      response.setHeader("Authorization", jwt);
    }
  }

  // 이 메서드는 필터가 실행될 특정한 조건을 정의한다. 여기서는 /login 경로에서만 이 필터가 실행됨.
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return !request.getServletPath()
                   .equals("/login");
  }
}
