package com.example.globalmethodsecurity16.service;

import com.example.globalmethodsecurity16.entity.Employee;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private Map<String, Employee> records =
      Map.of(
          "emma", new Employee("Emma Thompson", List.of("book1", "book2"), List.of("accountant", "reader")),
          "natalie", new Employee("Natalie Parker", List.of("book3"), List.of("researcher")));

  @PostAuthorize("returnObject.roles.contains('reader')")
  public Employee getBookDetails(String name) {
    return records.get(name);
  }

}
