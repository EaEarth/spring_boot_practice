package com.example.practice.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/noauth/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/noauth/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Content.";
    }

    @GetMapping("/auth")
    public String authAccess() {
        return "Authenticated Content.";
    }
}
