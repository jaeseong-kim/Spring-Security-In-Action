package com.example.smallandsecurewebapllication6.controller;

import com.example.smallandsecurewebapllication6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

  @Autowired
  private ProductService productService;

  @GetMapping("/main")
  public String main(Authentication auth, Model model){
    model.addAttribute("username",auth.getName());
    model.addAttribute("products",productService.findAll());

    return "main";
  }
}
