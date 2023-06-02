package com.example.responsibilitysegregation10.repository;

import com.example.responsibilitysegregation10.entity.Otp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, String> {

  Optional<Otp> findOtpByUsername(String username);
}
