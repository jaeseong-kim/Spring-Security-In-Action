package com.example.authenticationimplementation5.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

  @GetMapping("/hello")
  public String hello(Authentication auth) {
    return "hello, "+ auth.getName();
  }

  /*
  요청 스레드와 메소드 실행 스레드로 분리 된다
  분리된 스레드는 SecurityContext를 상속받지 않기 때문에
  Authorization은 null이 된다
  SecurityContextHoler.setStrategyName(MODE_INHERITABLETHREADLOCAL)로 해결 가능
  새로운 스레드의 기존 보안 컨텍스트 정보를 복사함
   */
  @GetMapping("/bye")
  @Async // 다른 스레드를 만듬
  public void goodbye(){
    SecurityContext context = SecurityContextHolder.getContext();
    String username = context.getAuthentication().getName();
  }

  @GetMapping("/ciao")
  public String ciao() throws Exception{
    Callable<String> task = () -> {
      SecurityContext context = SecurityContextHolder.getContext();
      return context.getAuthentication().getName();
    };

    ExecutorService e = Executors.newCachedThreadPool();
    try{
      var contextTask = new DelegatingSecurityContextCallable<>(task);
      return "Ciao, " + e.submit(contextTask).get()+"!";
    }finally {
      e.shutdown();
    }
  }

  @GetMapping("/hola")
  public String hola() throws Exception{
    Callable<String> task = () -> {
      SecurityContext context = SecurityContextHolder.getContext();
      return context.getAuthentication().getName();
    };

    ExecutorService e = Executors.newCachedThreadPool();
    e = new DelegatingSecurityContextExecutorService(e);
    try {
      return "Hola, "+ e.submit(task).get()+"!";
    }finally {
      e.shutdown();
    }
  }
}
