# 10장 CSRF 보호와 CORS 적용

## CSRF(Cross Site Request Forgery) 개념
    1. 사용자가 로그인을 한다.
    2. 공격자로부터 위조 스크립트가 있는 링크를 받는다.
    3. 사용자가 알지 못한 채 링크를 클릭한다.
    4. 사용자 위조 스크립트가 실행되고 파일이 공격자에게 전송된다.

## CSRF 보안
    스프링 시큐리티에서 csrf().disable()을 하지 않으면 HTTP POST로 엔드포인트를 호출할 수 없다.
    CSRF를 활성화한다는 것은 프론트엔드 변경 작업(GET, HEAD, TRACE, OPTIONS)만 할 수 있기 때문이다.

## CSRF 토큰
    CSRF 토큰이란 클라이언트에서 POST를 보낸다는 것은 적어도 POST이전에 GET 요청은 한 번은 있다는 것을 이용한 것이다.
    클라이언트가 GET요청을 하면 이 때, 서버에서 CSRF 토큰을 생성하고 클라이언트에게 넘기면 이 후 POST요청에 토큰을 넣고 보내면
    서버는 정상적인 요청으로 인식하고 요청을 받아들인다.

## CsrfFilter
    CsrfFilter는 토큰을 관리하는 필터역할을 한다.
    기본적으로 HTTP의 GET, HEAD, TRACE, OPTIONS 요청은 허용하고 그 외의 요청은 헤더에 토큰이 있는지 확인한다.
    클라이언트는 GET으로 페이지를 받고 CSRF 토큰은 헤더에 X-CSRF-TOKEN 이름으로 저장된다.
    
    필터에서 토큰을 저장하는 방법은 세션ID에 저장하는 방법과 레포지토리에 저장하는 방법이 있다.
    요청이 많을수록 세션보다 레포지토리에 저장하는 게 좋다.
    
## csrf 인증 흐름
    HTTP GET으로 페이지를 요청할 때, csrfFilter는 요청 헤더에 토큰을 넣고 csrfRepository에 토큰을 저장한다.
    클라이언트는 csrf 토큰이 담긴 응답을 받고 POST 요청을 보낼 때 토큰과 함께 보낸다.
    서버는 클라이언트가 보낸 요청에 토큰이 담겨있는지 확인하고 csrfRepository의 토큰과 일치하는지 비교한다.
    일치하면 POST 요청을 수락한다.

## CORS(Cross Origin Resource Sharing) 개념
    브라우저가 로드된 사이트의 도메인 이외의 도메인을 허용하지 않는 것을 말한다.
    예를 들어 브라우저가 example.com의 페이지를 로드 받으면, 브라우저는 이 사이트에서 example.org에 대해
    요청을 허용하지 않는다.

## CORS 거절 작동 예시
    1. example.com 페이지가 로드된 브라우저에서 example.org에 요청을 보낸다.
    2. example.org에서 응답을 보내온다.
    3. 응답 헤더에는 Access-Control-Allow-Origin이 없다.
    4. 브라우저는 응답을 받지 않는다.

## CORS 허락 작동 예시
    1. example.com 페이지가 로드된 브라우저에서 example.org에 요청을 보낸다.
    2. example.org에서 응답을 보내온다.
    3. 응답 헤더에는 Access-Control-Allow-Origin : example.com 이 있다.
    4. 브라우저는 로드된 페이지 example.com 주소와 일치하니 응답을 받아들인다.

## CrossOrigin 어노테이션
    해당 어노테이션으로 원하는 엔드포인트에 설정할 수 있다.
    하지만 설정해야할 엔드포인트가 많아질수록 코드가 복잡해진다.

    @GetMapping("/test")
    @CrossOrigin("http://computer1.com")
    public void test(){
        return "test";
    }
    
    이렇게 설정하고 computer1.com을 열고 있는 브라우저에서 test엔드포인트롤 호출하면
    헤더에 Access-Control-Allow-Origin에 http://computer1.com을 담고 응답을 보낸다.
    computer1.com을 열고 있는 브라우저는 Access-Control-Allow-Origin에 있는 주소를 보고
    응답을 받아들인다.

## CorsConfigurer
    한 곳에서 cors를 적용할 수 있도록 해준다.
    허용 출처, HTTP 메소드를 설정할 수 있다.
    
    @Configuration
    public Class Config extends WebSecurityConfigurerAdapter {
        
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors(설정 코드 작성);
        }
    }
    

    

    