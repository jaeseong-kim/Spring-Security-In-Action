package com.example.responsibilitysegregation10.controller;

import com.example.responsibilitysegregation10.entity.Otp;
import com.example.responsibilitysegregation10.entity.User;
import com.example.responsibilitysegregation10.service.UserService;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/user/add")
  public void addUser(User user) {
    userService.addUser(user);
  }

  @PostMapping("/user/auth")
  public void auth(@RequestBody User user) {
    log.info("user.getUsername() : " + user.getUsername() + "user.getPassword() : "
        + user.getPassword());

    userService.auth(user);
  }

  @PostMapping("/otp/check")
  public void check(@RequestBody Otp otp, HttpServletResponse response) {
    if (userService.check(otp)) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
  }
}
