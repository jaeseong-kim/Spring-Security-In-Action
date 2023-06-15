# 13장 OAuth 2: 권한 부여 서버 구현

    권한 부여 서버는 클라이언트가 리소스 서버에서 필요한 리소스를 얻기 전에 
    인증 절차를 거치도록 한다.
    1. 권한 부여서버에 클라이언트를 등록한다.
    2. 등록 후, clientId와 secret key를 얻는다.
    3. clientId와 secret key를 가지고 권한 부여 서버에 클라이언트를 인증하고 액세스 토큰을 얻는다.
    
## 권한 부여서버에 클라이언트 등록

    이 과정은 OAuth2를 이용하기 전에, 클라이언트를 권한 부여 서버에 등록하는 과정이다. 마치 사용자가
    홈페이지에 회원가입을 하고 인증을 받고 홈페이지의 서비스를 이용하는 것과 비슷하다.
    권한 부여 서버에 클라이언트를 등록하면 clientId와 secret key를 얻는데, 이것을 가지고 OAuth2를 사용할 때
    클라이언트를 증명하게 된다.

## 권한 부여 서버 만들기

    (주의할 점: spring boot와 spring cloud와의 버전이 서로 호환되도록 설정해야한다. 
    구글에 spring boot spring cloud version mapping 참고)
    build.gradle에 'org.springframework.cloud:spring-cloud-starter-oauth2'을 추가한다.
    
    권한 부여 서버 설정 클래스를 만든다.

    @Configuration
    @EnableAuthorizationServer
    public class Config extends AuthorizationServerConfigurerAdapter {
        코드 작성
    }

## 스프링 시큐리티에 클라이언트 등록하기
    
    ClientDetailsService : 인터페이스이고 UserDetailsService처럼 클라이언트를 찾는다.
    InMemoryClientDetailsService : ClientDetailsService의 구현 객체, 메모리로 클라이언트 관리한다.
    JdbcClientDetailsService : ClientDetailsService의 구현 객체, 데이터베이스로 클라이언트 관리한다.
    ClientDtails : 인터페이스이고 UserDetails처럼 클라이언트는 이 인터페이스를 이용해 구현한다.
    BaseClientDetails : ClientDtails를 구현한 객체다.
    
    @Configuration
    @EnableAuthorizationServer
    public class Config extends AuthorizationServerConfigurerAdapter {
        
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            방법 1
            var service = new InMemoryClientDetailsService();
            var cd = new BaseClientDetails();
            cd.setClientId("클라이언트ID");
            cd.setClientSecret("클라이언트 Secret");
            cd.setScope("권한");
            cd.setAuthorizedGrantType("Grant 방식");
            service.setClientDetailsStore(Map.of("client",cd));
            clients.withClientDetails(service);
            
            방법 2
            clients.inMemory()
            .withClient("클라이언트ID")
            .secret("클라이언트 Secret")
            .authorizedGrantTypes("password")
            .scopes("권한");
        }
    }

## 그랜트 유형
    
    모든 그랜트의 공통점은 클라이언트가 권한 부여 서버에 등록되어 있어야 한다.

### 비밀번호 그랜트

    사용자는 클라이언트에게 사용자 자격 증명을 제공한다.
    클라이언트는 권한 부여 서버에게 사용자 자격 증명과 클라이언트 자격 증명을 보낸다.
    권한 부여 서버는 인증을 성공한 후 토큰을 발급한다.

### 승인 코드 그랜트
    
    사용자가 권한 부여 서버에 사용자 자격 증명을 인증한다.
    인증이 완료되면 권한 부여 서버는 클라이언트에게 승인 코드를 발급한다.
    클라이언트는 승인코드로 권한 부여 서버에게서 액세스 토큰을 발급 받는다.

### 클라이언트 자격 증명 그랜트
    
    사용자의 자격 증명 없이 클라이언트의 자격 증명을 권한 부여 서버에게 보낸다.
    인증이 완료되면 액세스 토큰을 클라이언트에게 발급한다.
    

## 권한 부여 서버에서 액세스 토큰 받기
    
    권한 서버의 /oauth/token로 post를 날리면 액세스 토큰을 받을 수 있다.
    보낼 때에는 그랜트 유형에 따라 보내야할 파라미터가 정해진다.
    기본적으로 grant_type과 scope 포함.

## 갱신 토큰
    
    갱신 토큰은 암호 그랜트, 승인 코드 그랜트 유형에 사용된다.
    갱신 토큰을 사용하기 위해서는 authorizedGrantTypes("refresh_token")에 추가해야 한다.