package com.example.demo.Users.controller;

import com.example.demo.Users.model.User;
import com.example.demo.Users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
  @Autowired
  UsersRepository usersRepository;

  @PostMapping(value = "/login")
  public ResponseEntity<User> loginUser(@RequestBody User requestedUser) {
    User user = usersRepository.findByUsernameAndPassword(requestedUser.getUsername(), requestedUser.getPassword());

    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(null);
  }
}
