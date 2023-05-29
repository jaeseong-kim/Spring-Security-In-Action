# 9장 필터 구현

    스프링 시큐리티는 애플리케이션에 요청을 전달하기 전에 필터가 요청을 가로챈다.
    필터는 여러 개의 필터가 존재하며 하나의 필터가 끝나면 다음 필터가 실행된다.
    이 필터들에는 순서가 있는데 원하는 필터를 만들어서 원하는 순서에 필터를 적용시킬 수 있다.

## CustomFilter 구현

    새로운 필터를 만드려면 filter 인터페이스를 사용하면 된다.
    public CustomFilter implements Filter {
        
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
            //작성 코드

            // 다음 필터로 진행
            chain.doFilter(reqeust, response);
        }
    }

## 필터 장착

    @Configuration
    public class config extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.addFilterBefore(new CustomFilter1(), 스프링 시큐리티 필터1) --> 필터1 앞에 위치  
                .addFilterAfter(new CustomFilter2(), 스프링 시큐리티 필터2) --> 필터2 뒤에 위치
                .addFilterAt(new CustomFilter3(), 스프링 시큐리티 필터3) --> 필터3과 동일한 순서에 위치
        }
    }

    addFilterAt()은 새로 만든 필터를 기존 필터와 동일한 순서에 위치시키고 순서는 보장하지 않는다.
    기존 필터를 대체하는 개념이 아니다.

## OncePerRequestFilter
    스프링 시큐리티가 제공하는 필터로 요청 당 한 번 실행되도록 하는 필터다.
    - Http 요청만 지원
    - 비동기 요청, 오류 발송 요청에는 적용되지 않는다. -> shouldNoFilterAsyncDispatch(), shouldNoFilterErrorDispathch()로 구현
    