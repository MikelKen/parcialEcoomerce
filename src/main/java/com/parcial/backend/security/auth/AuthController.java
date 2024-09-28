package com.parcial.backend.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping(value = "signin")
    public ResponseEntity<AuthResponse> signup(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping(value = "signup")
    public ResponseEntity<AuthResponse> signin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    

  

}
