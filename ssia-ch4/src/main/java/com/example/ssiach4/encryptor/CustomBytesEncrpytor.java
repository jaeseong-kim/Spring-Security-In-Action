package com.example.ssiach4.encryptor;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;

public class CustomBytesEncrpytor implements BytesEncryptor {

  private final String password;
  private final String salt;

  public CustomBytesEncrpytor(String password, String salt){
    this.password = password;
    this.salt = salt;
  }
  @Override
  public byte[] encrypt(byte[] byteArray) {
    return Encryptors.standard(password,salt).encrypt(byteArray);
  }

  @Override
  public byte[] decrypt(byte[] encryptedByteArray) {
    return Encryptors.standard(password,salt).decrypt(encryptedByteArray);
  }
}
