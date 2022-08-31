package com.codingpals.hotel.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SecurityController {
  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/")
  public String connect() {
    return "you're connected !";
  }
}
