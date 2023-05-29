package com.example.filterimplementation9.config.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//클라이언트와 서버의 정적 키를 이용한 인증
@Component
public class StaticKeyAuthenticationFilter implements Filter {

  @Value("${authorization.key}")
  private String authorizationKey;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    var httpRequest = (HttpServletRequest) request;
    var httpResponse = (HttpServletResponse) response;

    String authentication = httpRequest.getHeader("Authorization");

    if (authorizationKey.equals(authentication)) {
      // 키가 일치하면 다음 필터로 진행
      chain.doFilter(request, response);
    } else {
      // 일치하지 않으면 미인증 처리
      httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}
