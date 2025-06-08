package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("POST /LOGIN");
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            String jwt = jwtService.generateJwt(authentication.getName());
            return ResponseEntity.ok(LoginResponse.builder().token(jwt).build());
        } catch (Exception e) {
            System.out.println("AUTH ERROR: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginResponse.builder()._error(e.getMessage()).build());
        }
    }
    @GetMapping("/users")
    public List<String> getUsers() {
        return userRepository.getUsers().stream().map(User::getUsername).toList();
    }
}
