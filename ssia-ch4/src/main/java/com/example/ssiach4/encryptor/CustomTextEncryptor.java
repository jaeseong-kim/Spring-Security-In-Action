package com.example.ssiach4.encryptor;

import com.example.ssiach4.keygenerator.CustomStringKeyGenerator;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class CustomTextEncryptor implements TextEncryptor {

  private final String password;
  private final String salt;

  public CustomTextEncryptor(String password, String salt) {
    this.password = password;
    this.salt = salt;
  }

  @Override
  public String encrypt(String text) {
    return Encryptors.text(password, salt).encrypt(text);
  }

  @Override
  public String decrypt(String encryptedText) {
    return Encryptors.text(password,salt).decrypt(encryptedText);
  }
}
