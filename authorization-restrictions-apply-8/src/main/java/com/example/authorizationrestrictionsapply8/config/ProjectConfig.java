package com.example.authorizationrestrictionsapply8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService userDetailsService() {
    var manager = new InMemoryUserDetailsManager();

    UserDetails user1 = User.withUsername("john")
                            .password("12345")
                            .authorities("read")
                            .build();

    UserDetails user2 = User.withUsername("jane")
                            .password("12345")
                            .authorities("read", "premium")
                            .build();

    manager.createUser(user1);
    manager.createUser(user2);

    return manager;
  }

  @Bean
  public PasswordEncoder encoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable();
    http.httpBasic();

    http.authorizeRequests()
        .regexMatchers(".*/(us|uk|ca)+/(en|fr).*")
        .authenticated()
        .anyRequest()
        .hasAnyAuthority("premium");
  }
}
