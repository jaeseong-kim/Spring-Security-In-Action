# 7장 권한 부여 구성: 액세스 제한

## 권한과 역할
    사용자는 서비스로부터 인증을 받고 난 뒤에 사용자가 누구냐에 따라 권한을 받거나 역할을 받기도 한다.
    권한과 역할에 따라 사용자는 A 엔드포인트에는 접근이 가능하고 B 엔드포인트에는 접근이 불가능할 수 있다.
    권한은 읽기, 쓰기, 수정, 삭제와 같은 것을 말하며 역할은 관리자, 매니저와 같은 것을 말한다.
    
    권한과 역할의 관계는 다음과 같다.
    관리자는 읽기, 쓰기, 수정, 삭제의 모든 권한을 가지고 매니저는 읽기, 쓰기의 권한만 가질 수 있다고 하면,
    역할은 권한을 아우르는 개념이라고 할 수 있을 것 같다.

## 권한과 역할 구현
    권한이나 역할은 모두 UserDetails의 getAuthorites()에서 구현된다.
    getAuthorites()는 GrantedAuthority를 상속한 객체 컬렉션을 반환한다.
    
    public interface UserDeatails extends Serializable {
        Collection<? extends GrantedAuthority> getAuthorities();
    }

## 권한으로 엔드포인트 접근 제한
    권한으로 접근 제한하는 방법은 다음과 같이 3가지가 있다.

    hasAuthority("READ") : 읽기 권한을 가진 사람이 접근 가능 
    hasAnyAuthority("READ","WRITE") : 읽기 또는 쓰기 권한 가진 사람만이 접근 가능
    access("hasAnyAuthority('WRITE')") : 쓰기 권한 가진 사람만이 접근 가능
    access()는 나머지 방법으로 구현할 수 없을 경우에만 사용하는 것이 좋다.
    코드가 더 복잡해지기 때문이다.

## 역할로 엔드포인트 접근 제한
    사용자에게 역할을 줄 경우 접두사 ROLE_을 붙여야 한다. 하지만 역할을 이용해 접근 제한을 할 경우,
    ROLE_을 빼야한다.
    
    역할로 접근 제한하는 방법은 다음과 같이 3가지가 있다.

    hasRole("ADMIN") : 관리자만 접근 가능
    hasAnyRole("ADMIN","MANAGER") : 관리자 또는 매니저만 접근 가능
    access() : 나머지 방법이 불가능할 때 사용해야함.