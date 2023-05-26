# 8장 권한 부여 구성: 제한 적용
    경로에 따라 특정 권한을 가진 사람만 접근할 수 있게 하려면 선택기 메소드로 정해주어야 한다.

## 선택기 메소드
    선택기 메소드에는 3가지가 있다.
    - MVC 선택기 : MVC 식을 이용해 경로 설정
    - 앤트 선택기 : 앤트 식을 이용해 경로 설정
    - 정규식 선택기 : 정규식을 이용해 경로 설정

## MVC Matchers
    메소드는 2가지가 있다.
    - mvcMatchers(HttpMethod method, String... patterns) : 경로와 HTTP 메소드로 경로를 접근 제한을 지정한다.
    - mvcMatchers(String... patterns) : 경로로만 접근 제한을 지정한다. 모든 HTTTP 메소드에 적용된다.
    
    antMathcers보다 이 선택기를 쓰는 것이 보안상 더 안전하다.    

## 앤트 Matchers
    MVC Matchers와의 차이점은 AntMatchers로 /hello 경로를 지정하면 /hello/는 보호되지 않는다는 점이다.
    
    메소드는 3가지가 있다.
    - antMatchers(HttpMethod method, String patterns) : 경로와 HTTP 메소드로 경로 지정
    - antMatchers(String patterns) : 경로로만 지정하기에 모든 HTTP 메소드에 적용
    - antMatchers(HttpMethod method) : HTTP 메소드로만 정하기에 모든 경로에 적용

## 정규식 선택기
    정규식 선택기는 위 2가지 선택기로도 불가능한 경우에만 사용하는 것이 좋다.
    
    메소드는 2가지가 있다.
    - regexMatchers(HttpMethod method, String regex) : 경로와 HTTP 메소드로 경로를 설정
    - regexMatchers(String regex) : 경로로만 설정하기에 모든 HTTP 메소드에 적용