# 4장 암호처리

## PasswordEncoder 인터페이스
    - encode() : 비밀번호를 암호화
    - matches() : 입력 비밀번호와 암호화된 비밀번호가 일치하는지 확인
    - upgradeEncoding() : 암호화된 비밀번호를 한 번 더 인코딩

## PasswordEncoder 구현체
    - NoOpPasswordEncoder : 비밀번호를 평문 텍스트로 그냥 저장하기 때문에 실사용은 금지
    - StandardPasswordEncoder : SHA-256 해싱 알고리즘을 사용하지만 강도가 약함.
    - Pbkdf2PasswordEncoder : PBKDF2를 이용
    - BCryptPasswordEncoder : bcrypt 강력 해싱 함수 사용
    - SCryptPasswordEncoder : scrypt 해싱 함수 사용

## DelegatingPasswordEncoder
    DelegatingPasswordEncoder로 PasswordEncoder를 구현체들을 사용할 수 있다.
    현재 사용하고 있는 암호 알고리즘에 취약점이 발견되어 바꿔야 하는 상황이라면
    기존의 알고리즘을 새로운 알고리즘으로 바꾸기 쉽지 않을 것이다.
    그럴 때 DelegatingPasswordEncoder를 사용해서 사용하고자 하는 PasswordEncoder의
    구현체를 DelegatingPasswordEncoder에 넣어주면 적절하게 사용할 수 있다.

## SSCM의 두 가지 기능
    
### 키 생성기
    - 암호화나 해싱 알고리즘에 사용, KeyGeneraotr를 통해 키 생성기를 만들 수 있다.
    - StringKeyGenerator : 문자열 키를 생성
    - BytesKeyGenerator : byte[] 키를 생성

### 암호기
    - TextEncryptor : 데이터를 문자열로 암호화
    - BytesEncryptor : 데이터를 byte[]로 암호화