package com.example.csrfandcors10.controller;

import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  private Logger logger = Logger.getLogger(MainController.class.getName());
  @GetMapping("/main")
  public String main(){
    return "main";
  }

  @GetMapping("/main2")
  public String main2(){
    return "main2";
  }

  @PostMapping("/test")
  @ResponseBody
  public String test(){
    logger.info("test method called");
    return "test";
  }
}
