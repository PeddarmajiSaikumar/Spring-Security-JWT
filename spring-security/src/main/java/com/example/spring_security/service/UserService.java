package com.example.spring_security.service;

import com.example.spring_security.model.Users;
import com.example.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        return userRepository.save(user);
    }


    public String login(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null) {
            return "User not found";
        }
        if (passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            return "Login successful";
        }
        return "Login failed";
    }
}
