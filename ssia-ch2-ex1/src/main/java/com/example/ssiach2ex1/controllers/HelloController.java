package com.example.ssiach2ex1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 1. 스프링 컨텍스트에 빈 등록 2. 웹 컨트롤러로 사용 3. HTTP 응답 본문에 값을 설정
public class HelloController {

  // HTTP basic 인증 과정
  /*
  1. "username:비밀번호"를 base64로 인코딩
  2. HTTP 헤더 Authorization 값에 인증 종류인 Basic 입력
  2. 인코딩 값 입력
  3. 요청을 보냄
   */
  @GetMapping("/hello")
  public String hello(){
    return "Hello";
  }

}
