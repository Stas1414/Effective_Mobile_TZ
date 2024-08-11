package com.example.Task.Management.controller;

import com.example.Task.Management.Jwt.JwtService;
import com.example.Task.Management.model.User;
import com.example.Task.Management.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "It is used to register and receive a JWT token")
public class AuthController {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserServiceImpl userService;

    @Autowired
    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, UserServiceImpl userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Operation(summary = "Getting a jwt token")
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

    @Operation(summary = "User registration")
    @PostMapping("/registration")
    public ResponseEntity<Void> registration(@RequestBody User newUser) {
        userService.saveNewUser(newUser);
        return ResponseEntity.noContent().build();
    }
}
