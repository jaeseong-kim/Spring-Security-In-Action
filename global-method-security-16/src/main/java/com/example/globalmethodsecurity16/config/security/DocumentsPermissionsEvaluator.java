package com.example.globalmethodsecurity16.config.security;

import com.example.globalmethodsecurity16.entity.Document;
import com.example.globalmethodsecurity16.repository.DocumentRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class DocumentsPermissionsEvaluator implements PermissionEvaluator {

  @Autowired
  private DocumentRepository documentRepository;


  @Override
  public boolean hasPermission(Authentication auth, Object subject, Object permission) {
    Document document = (Document) subject;
    String p = (String) permission;

    boolean admin = auth.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority()
                                        .equals(p));

    return admin || document.getOwner()
                            .equals(auth.getName());
  }

  @Override
  public boolean hasPermission(Authentication a, Serializable id, String type, Object permission) {

    String code = id.toString();
    Document document = documentRepository.findDocument(code);

    String p = (String) permission;

    boolean admin = a.getAuthorities()
                     .stream()
                     .anyMatch(auth -> auth.getAuthority()
                                           .equals(p));

    return admin || document.getOwner()
                            .equals(a.getName());
  }
}
