package com.example.ssiach4.keygenerator;

import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class CustomStringKeyGenerator implements StringKeyGenerator{

  private final StringKeyGenerator stringKeyGenerator;

  public CustomStringKeyGenerator(){
    this.stringKeyGenerator = KeyGenerators.string();
  }

  @Override
  public String generateKey() {
    return stringKeyGenerator.generateKey();
  }
}
