package com.example.authenticationimplementation5.config;

import com.example.authenticationimplementation5.security.CustomAuthenticationFailureHandler;
import com.example.authenticationimplementation5.security.CustomAuthenticationSuccessHandler;
import com.example.authenticationimplementation5.security.CustomEntryPoint;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableAsync
public class AuthConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
  @Autowired
  private CustomAuthenticationFailureHandler authenticationFailureHandler;

  //SecurityContextHoloer의 전략을 MODE_THREADLOCAL -> MODE_INHERITABLETHREADLOCAL로 변경
  //새로 생긴 스레드에 SecurityContext도 복사됨.
  @Bean
  public InitializingBean initializingBean() {
    return () -> SecurityContextHolder.setStrategyName(
        SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
    );
  }

  /*
  타입은 AuthenticationProvider이지만
  @Component로 등록된 CustomAuthenticationProvider가 알아서 연결
   */
  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // 인증방식을 http basic에서 formLogin으로 변경
    http.formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler)
        .and()
        .httpBasic();

    http.authorizeRequests()
        .anyRequest()
        .authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }
}