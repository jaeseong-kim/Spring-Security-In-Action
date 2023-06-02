package com.example.responsibilitysegregation10buisnessserver.config;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {


  // 매개변수가 2개인 생성자가 호출되면 setAuthenticated(false);으로 설정되서 인증 완료되지 않은 상태
  public UsernamePasswordAuthentication(Object principal, Object credentials) {
    super(principal, credentials);
  }

  // 매개변수가 3개인 생성자가 호출되면 setAuthenticated(true);으로 설정되어 인증 완료 상태
  public UsernamePasswordAuthentication(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
