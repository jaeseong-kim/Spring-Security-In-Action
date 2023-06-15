package com.example.authorizationserveroauth213.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
@EnableAuthorizationServer // OAuth2 권한 부여 서버 활성화
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

    // 인증 서버에서 authenticationManager에 등록된 사용자를 인증할 수 있다.
    endpoints.authenticationManager(authenticationManager);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

    /*
    클라이언트를 권한 부여 서버에 등록하는 방법

    방법 1
    var service = new InMemoryClientDetailsService();
    var cd = new BaseClientDetails();
    cd.setClientId("client");
    cd.setClientSecret("secret");
    cd.setScope(List.of("read"));
    cd.setAuthorizedGrantTypes(List.of("password"));

    //InMemoryClientDetailsService에 ClientDetails 추가
    service.setClientDetailsStore(Map.of("client", cd));

    clients.withClientDetails(service);

     */

    //방법 2
    /*
    clients.inMemory()
           .withClient("client1")
           .secret("secret1")
           .authorizedGrantTypes("authorization_code")
           .scopes("read")
           .redirectUris("http://localhost:9090/home")
           .and()
           .withClient("client2")
           .secret("secret2")
           .authorizedGrantTypes("authorization_code", "password", "refresh_token")
           .scopes("read")
           .redirectUris("http://localhost:9090/home");

     */

    clients.inMemory()
           .withClient("client")
           .secret("secret")
           .authorizedGrantTypes("password", "refresh_token")
           .scopes("read");

  }
}
