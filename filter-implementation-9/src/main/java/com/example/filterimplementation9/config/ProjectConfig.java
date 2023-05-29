package com.example.filterimplementation9.config;

import com.example.filterimplementation9.config.filters.StaticKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private StaticKeyAuthenticationFilter filter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 기존 필터 위치에 추가했다고 해서 필터가 대체되는 것이 아님. 같은 위치에 필터가 여러개 있는 것일 뿐, 순서를 보장하지 않는다.
    http.addFilterAt(filter, BasicAuthenticationFilter.class)
        .authorizeRequests()
        .anyRequest()
        .permitAll();

  }
}
