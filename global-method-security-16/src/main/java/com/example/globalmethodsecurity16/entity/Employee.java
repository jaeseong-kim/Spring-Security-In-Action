package com.example.globalmethodsecurity16.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

  private String name;
  private List<String> books;
  private List<String> roles;

  public Employee(String name, List<String> books, List<String> roles) {
    this.name = name;
    this.books = books;
    this.roles = roles;
  }
}
