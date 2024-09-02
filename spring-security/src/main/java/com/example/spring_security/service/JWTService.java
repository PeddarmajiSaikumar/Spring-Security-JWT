package com.example.spring_security.service;

import org.springframework.stereotype.Service;

@Service
public class JWTService {
    public String generateToken() {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InNhaWt1bWFyIiwiaWF0IjoxNTE2MjM5MDIyfQ.oRqHt4uesFmat0IRU9e56lsXqAJqW9sK0AXjsS0lNh8";
    }
}
