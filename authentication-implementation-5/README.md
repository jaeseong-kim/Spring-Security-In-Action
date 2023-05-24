# 5장 인증구현

## Authentication 인터페이스
    - Principal을 확장한 인터페이스다.
    - 인증된 사용자의 이름, 비밀번호, 권한 등을 저장한다.
    - 인증이 성공된 후, Authentication을 통해 인증된 사용자 정보를 이용할 수 있다.

## AuthenticationProvider 인터페이스
    - AuthenticationProvider는 사용자를 인증하는 과정을 처리한다.
    - 인터페이스를 구현하면 authenticate()와 supoort()를 오버라이딩해야 한다.
    - support()로 어떤 Authentication 객체를 지원할 지 결정할 수 있다.
    - authenticate()는 userDetailsService 인터페이스에게 사용자를 찾고 PasswordEncoder로 비밀번호를 검증한다.
    - 인증이 실패하면 AuthencticationException을 상속한 UsernameNotFoundException, BadCredentialsException를 날린다.
    - 인증이 성공하면 Authentication을 구현한 객체에 정보를 집어 넣어서 반환한다.
    - Authentication 구현 객체는 UsernamePasswordAuthenticationToken이 있다.

## SecurityContext 인터페이스
    - AuthenticationProvider에서 인증이 성공하면,
      사용자 정보는 SecurityContext에 Authenctication으로 저장된다.

## SecurityContextHolder
    - SecurityContext를 관리하는 클래스다.
    - 관리 전략에는 MODE_THREADLOCAL, MODE_INHERITABLETHREADLOCAL, MODE_GLOBAL이 있다.
    - MODE_THREADLOCAL은 스레드는 자신의 SecurityContext에만 접근할 수 있다.
    - MODE_INHERITABLETHREADLOCAL은 비동기 호출을 할 때 사용된다.
      비동기 호출을 할 경우, 스레드가 메소드와 요청 스레드로 나뉘어지는데,
      MODE_INHERITABLETHREADLOCAL로 전략방식을 정하면 새로 생성된 스레드에
      SecurityContext도 복사되어 사용자 정보를 사용할 수 있다.
    - MODE_GLOBAL은 모든 스레드가 하나의 SecurityContext에 접근할 수 있도록 하는 전략이다.
    - 사용자가 직접 새로 생성한 스레드일 경우에는 위 전략방식으로는 해결할 수 없다.
    
## 사용자가 직접 생성한 스레드일 경우, SecurityContext 전달 방법
    - DelegatingSecurityContextExecutor
    - DelegatingSecurityContextExecutorService
    - DelegatingSecurityContextScheduledExecutorService
    - DelegatingSecurityContextRunnable
    - DelegatingSecurityContextCallable

## AuthenticationSuccessHandler 인터페이스
    - 사용자가 인증이 성공하면 이후 로직을 자세하게 설정할 수 있다.
    - 성공할 경우 사용자 정보에 따라 이동하는 페이지를 설정할 수 있다.

## AuthenticationFailureHandler 인터페이스
    - 사용자가 인증이 실패하면 이후 로직을 자세하게 설정할 수 있다.