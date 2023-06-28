package com.example.globalmethodsecurity16.service;

import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {

  private Map<String, List<String>> secretNames = Map.of("natalie", List.of("Energico", "Perfecto"),
      "emma", List.of("Fantastico"));

  @PreAuthorize("hasAuthority('write')")
  public String getName() {
    return "jaes";
  }

  @PreAuthorize("#name == authentication.principal.username")
  public List<String> getSecretNames(String name) {
    return secretNames.get(name);
  }
}
