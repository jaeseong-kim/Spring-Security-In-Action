package com.example.ssiach4;

import com.example.ssiach4.encryptor.CustomBytesEncrpytor;
import com.example.ssiach4.encryptor.CustomTextEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

@SpringBootApplication
public class SsiaCh4Application {

  public static void main(String[] args) {

    //SpringApplication.run(SsiaCh4Application.class, args);

    String salt = KeyGenerators.string()
                               .generateKey();
    String plainText = "hello";
    String password = "12345";

    TextEncryptor textEncryptor = new CustomTextEncryptor(password, salt);

    System.out.println("plainText : " + plainText);
    String encryptedText = textEncryptor.encrypt(plainText);
    System.out.println("encrypt(plainText) : "+ encryptedText);
    System.out.println("decrypt(encryptedText) : "+textEncryptor.decrypt(encryptedText));


    BytesEncryptor bytesEncryptor = new CustomBytesEncrpytor(password,salt);
    System.out.println("------------------------");
    byte[] encryptedByte=bytesEncryptor.encrypt(plainText.getBytes());
    for (int i = 0; i < encryptedByte.length; i++) {
      System.out.print(encryptedByte[i]+" ");
    }
    System.out.println();
    System.out.println("encrypt(plainText) : "+ encryptedByte);

    System.out.println("decrypted(plainText) : "+bytesEncryptor.decrypt(encryptedByte));

  }

}
