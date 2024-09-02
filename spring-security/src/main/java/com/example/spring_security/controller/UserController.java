package com.example.spring_security.controller;

import com.example.spring_security.model.Users;
//import okhttp3.ResponseBody;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.service.UserService;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user){
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.Verify(user);
    }
}

