package com.example.globalmethodsecurity16.config;

import com.example.globalmethodsecurity16.config.security.DocumentsPermissionsEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class config extends GlobalMethodSecurityConfiguration {

  @Autowired
  private DocumentsPermissionsEvaluator evaluator;

  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    var expressHandler = new DefaultMethodSecurityExpressionHandler();

    expressHandler.setPermissionEvaluator(evaluator);

    return expressHandler;
  }


  @Bean
  public UserDetailsService userDetailsService() {

    var uds = new InMemoryUserDetailsManager();

    var u1 = User.withUsername("natalie")
                 .password("12345")
                 .roles("admin")
                 .build();

    var u2 = User.withUsername("emma")
                 .password("12345")
                 .roles("manager")
                 .build();

    uds.createUser(u1);
    uds.createUser(u2);

    return uds;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }


}
