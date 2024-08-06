package com.example.Task.Management.controller;

import com.example.Task.Management.Jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String
            password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.GenerateToken(username));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
