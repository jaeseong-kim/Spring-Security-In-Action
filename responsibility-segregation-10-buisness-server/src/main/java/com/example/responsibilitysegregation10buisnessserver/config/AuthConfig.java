package com.example.responsibilitysegregation10buisnessserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {



  @Bean
  public InitialAuthenticationFilter initialAuthenticationFilter() throws Exception {
    return new InitialAuthenticationFilter(authenticationManager());
  };

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private OtpAuthenticationProvider otpAuthenticationProvider;

  @Autowired
  private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // 인증 공급자 추가
    auth.authenticationProvider(otpAuthenticationProvider)
        .authenticationProvider(usernamePasswordAuthenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf()
        .disable();
    http.addFilterAt(initialAuthenticationFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

    http.authorizeRequests()
        .anyRequest()
        .authenticated();
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
