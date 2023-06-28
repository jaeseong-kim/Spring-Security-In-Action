package com.example.globalmethodsecurity16.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Document {

  private String owner;

  public Document(String owner) {
    this.owner = owner;
  }
}
