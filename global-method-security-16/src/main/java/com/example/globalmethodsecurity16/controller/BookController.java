package com.example.globalmethodsecurity16.controller;

import com.example.globalmethodsecurity16.entity.Employee;
import com.example.globalmethodsecurity16.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/book/details/{name}")
  public Employee getDetails(@PathVariable String name) {
    return bookService.getBookDetails(name);
  }
}
