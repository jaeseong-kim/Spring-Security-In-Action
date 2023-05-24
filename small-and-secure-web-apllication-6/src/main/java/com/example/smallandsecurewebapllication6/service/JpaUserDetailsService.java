package com.example.smallandsecurewebapllication6.service;

import com.example.smallandsecurewebapllication6.entity.User;
import com.example.smallandsecurewebapllication6.repository.UserRepository;
import com.example.smallandsecurewebapllication6.security.CustomUserDetails;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findUserByUsername(username)
                              .orElseThrow(() -> new UsernameNotFoundException("사용자 정보가 올바르지 않습니다."));

    return new CustomUserDetails(user);
  }
}
