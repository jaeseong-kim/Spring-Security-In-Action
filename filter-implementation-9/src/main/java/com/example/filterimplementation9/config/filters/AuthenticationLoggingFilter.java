package com.example.filterimplementation9.config.filters;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

// 로깅 필터를 한 번 요청에 여러번 호출될 수 있기때문에 Filter대신 OncePerRequestFilter를 이용하는 것이 좋다.
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

  private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

  // 이 필터는 HTTP만 지원하기 때문에 매개변수로 HttpServletRequest, HttpServletResponse가 있다.
  // 기본적으로 비동기 요청, 오류 발송 요청에는 적용 x
  // 적용하려면 shouldNoFilterAsyncDispatch or shouldNoFilterErrorDispathch를 재정의


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String requestId = request.getHeader("Request-Id");

    logger.info("Successfully authenticated request with id " + requestId);

    filterChain.doFilter(request, response);
  }
}
