package com.example.responsibilitysegregation10.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateCodeUtil {

  private GenerateCodeUtil() {

  }

  public static String generateCode() {
    String code;

    try {
      //임의의 int값을 생성하는 인스턴스
      SecureRandom random = SecureRandom.getInstanceStrong();

      // 1000 <= c <= 10000의 임의의 4자리 값
      int c = random.nextInt(9000) + 1000;

      code = String.valueOf(c);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    return code;
  }
}
