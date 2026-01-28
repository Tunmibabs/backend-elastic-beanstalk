package com.example.backend_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Application is healthy";
    }

    @GetMapping("/login")
    public String login() {
        return "Login endpoint working";
    }
}