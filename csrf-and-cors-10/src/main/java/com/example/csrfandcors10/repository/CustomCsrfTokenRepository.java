package com.example.csrfandcors10.repository;

import com.example.csrfandcors10.entity.Token;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {

  @Autowired
  private JpaTokenRepository jpaTokenRepository;

  @Override
  public CsrfToken generateToken(HttpServletRequest request) {
    String uuid = UUID.randomUUID()
                      .toString();

    return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf,", uuid);
  }

  @Override
  public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
    // 클라이언트는 HTTP 요청 헤더에 자신의 식별자를 담아 보낸다.
    // 서버는 식별자로 DB에 토큰이 있는지 확인
    // 있으면 업데이트 없으면 새로 저장

    String identifier = request.getHeader("X-IDENTIFIER");
    Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

    if (existingToken.isPresent()) {
      Token csrfToken = existingToken.get();
      csrfToken.setToken(token.getToken());
    } else {
      Token csrfToken = new Token();
      csrfToken.setToken(token.getToken());
      csrfToken.setIdentifier(identifier);
      jpaTokenRepository.save(csrfToken);
    }
  }

  @Override
  public CsrfToken loadToken(HttpServletRequest request) {
    String identifier = request.getHeader("X-IDENTIFIER");

    Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

    if (existingToken.isPresent()) {
      Token token = existingToken.get();
      return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
    }

    return null;
  }
}
