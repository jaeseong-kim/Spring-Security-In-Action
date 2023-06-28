# 16장 전역 메서드 보안: 사전 및 사후 권한 부여

## 전역 메서드 보안

## 전역 메서드 보안 활성화

    @EnableGlobalMethodSecurity : 전역 메소드 보안 활성화
    prePostEnabled : PreAuthorize, PostAuthorize 메소드 활성화
    @PreAuthorize(spel) : 메소드 호출 전 권한을 만족하는지 확인, 만족하지 못하면 예외 발생
    @PostAuthoriza(spel) : 메소드 호출 후 권한을 만족하는지 확인, 만족하지 못하면 예외 발생

### spel이 복잡할 경우
    
    spel이 복잡할 경우 spel을 만들기 보다는 별도의 클래스로 인터페이스 PermissionEvaluator를 구현해 
    원하는 메소드로 오버라이딩하면 된다.
    