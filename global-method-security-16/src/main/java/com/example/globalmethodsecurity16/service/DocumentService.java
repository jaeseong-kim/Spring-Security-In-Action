package com.example.globalmethodsecurity16.service;

import com.example.globalmethodsecurity16.entity.Document;
import com.example.globalmethodsecurity16.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

  @Autowired
  private DocumentRepository documnetRepository;

  @PreAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
  public Document getDocument(String code) {
    return documnetRepository.findDocument(code);
  }
}
