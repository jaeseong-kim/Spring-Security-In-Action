# 15장 OAuth 2: JWT와 암호화 서명 사용

## 비대칭 키
    비대칭 키란 암호화에 쓰이는 키와 복호화에 쓰이는 키가 서로 다른 키를 말한다.
    서로 다른 키를 사용하기 때문에 비대칭 키 쌍이라 말하고 공개키와 비밀키가 있다.
    비밀키는 토큰을 암호화할 때 쓰이고 공개키는 서명된 토큰을 복호화할 때 사용된다.
    
## 키 쌍 생성하기
    - 준비
    키 쌍을 생성하기 위해서 keytool과 openssl을 다운
    (git bash에는 openssl이 기본적으로 깔려 있기때문에 git bash도 사용 가능)
    
    - 비밀키 생성
    keytool -genkeypair -alias 키이름 -keyalg 사용알고리즘 -keypass 비밀번호 -keystore 저장할파일명.jks -storepass 비밀번호

    - 공개키 생성
    keytool -list -rfc --keystore 비밀키 파일 | openssl x509 -inform pem -pubkey

## 사용법

### 1. 권한 부여 서버에 비밀키 설정, 리소스 서버에 공개키 설정
        
    권한 부여에는 비밀키를 설정하고 리소스 서버에는 공개키를 설정해서 JWT를 검증한다.
    이런 구조는 키를 변경해야 할 때 양쪽에서 변경해야 하므로 보안에 취약하다.

### 2. 권한 부여 서버에 비밀키, 공개키 설정
    
    권한 부여 서버에서 비밀키, 공개키를 모두 관리하고 리소스 서버에 엔드포인트로 공개키를 노출시켜 JWT를
    검증하도록 한다. 이 구조는 키를 변경해야할 때, 쉽게 변경할 수 있고 보안에 좋다.
    
    리소스 서버는 권한 부여 서버에 /oauth/token_key 로 공개키를 얻어온다.

## TokenEnhancer
    토큰에 추가 정보를 넣을 수 있다.

    public class CustomTokenEnhancer implements TokenEnhancer {

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
        OAuth2Authentication authentication) {

            기존 토큰을 받는다.
            var token = new DefaultOAuth2AccessToken(accessToken);

            추가할 정보를 Map 객체로 만든다.
            Map<String, Object> info = Map.of("generatedInZone", ZoneId.systemDefault().toString());
            
            추가함
            token.setAdditionalInformation(info);

            return token;
        }
    }

    
    
    
    