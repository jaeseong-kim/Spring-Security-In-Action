package com.example.ssiach4.keygenerator;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

@Component
public class CustomBytesKeyGenerator implements BytesKeyGenerator {


  private final BytesKeyGenerator bytesKeyGenerator;
  public CustomBytesKeyGenerator(){
    this.bytesKeyGenerator = KeyGenerators.secureRandom();
  }
  @Override
  public int getKeyLength() {
    return bytesKeyGenerator.getKeyLength();
  }

  @Override
  public byte[] generateKey() {
    return bytesKeyGenerator.generateKey();
  }
}
