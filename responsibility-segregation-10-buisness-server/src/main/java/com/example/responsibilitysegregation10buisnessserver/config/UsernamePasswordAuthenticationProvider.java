package com.example.responsibilitysegregation10buisnessserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private AuthenticationServerProxy proxy;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials()
                                    .toString();

    log.info("username : " + username + ", password : " + password);
    proxy.sendAuth(username, password);

    return new UsernamePasswordAuthenticationToken(username, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
  }
}
