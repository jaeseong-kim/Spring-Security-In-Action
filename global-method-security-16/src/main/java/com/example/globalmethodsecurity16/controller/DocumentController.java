package com.example.globalmethodsecurity16.controller;

import com.example.globalmethodsecurity16.entity.Document;
import com.example.globalmethodsecurity16.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {


  @Autowired
  private DocumentService documentService;

  @GetMapping("/documents/{code}")
  public Document getDetails(@PathVariable String code) {
    return documentService.getDocument(code);
  }
}
