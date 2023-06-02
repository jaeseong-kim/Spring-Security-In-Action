package com.example.responsibilitysegregation10.repository;

import com.example.responsibilitysegregation10.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

  Optional<User> findUserByUsername(String username);
}
