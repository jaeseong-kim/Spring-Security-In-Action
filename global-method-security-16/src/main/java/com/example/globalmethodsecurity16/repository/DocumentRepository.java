package com.example.globalmethodsecurity16.repository;

import com.example.globalmethodsecurity16.entity.Document;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository {

  private Map<String, Document> documents =
      Map.of("abc123", new Document("natalie"),
          "qwe123", new Document("natalie"),
          "asd555", new Document("emma"));

  public Document findDocument(String code) {
    return documents.get(code);
  }
}
