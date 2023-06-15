package com.example.howtoworkoauth212.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    var c = clientRegistration();
    return new InMemoryClientRegistrationRepository(c);
  }


  private ClientRegistration clientRegistration() {
    return CommonOAuth2Provider.GITHUB.getBuilder("github")
                                      .clientId("af04195ca523ac28e074")
                                      .clientSecret("e1b017ac72a23e24b987b8aa223d3d8b5bc72a85")
                                      .build();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // oauth2Login을 설정하면 필터체인에 OAuth2LoginAuthenticationFilter를 추가함
    http.oauth2Login();

    http.authorizeRequests()
        .anyRequest()
        .authenticated();
  }
}
