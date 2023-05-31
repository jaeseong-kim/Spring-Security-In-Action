package com.example.csrfandcors10.repository;

import com.example.csrfandcors10.entity.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTokenRepository extends JpaRepository<Token,Integer> {

  Optional<Token> findTokenByIdentifier(String identifier);
}
