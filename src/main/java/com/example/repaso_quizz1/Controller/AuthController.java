package com.example.repaso_quizz1.Controller;

import com.example.repaso_quizz1.Auth.TokenResponse;
import com.example.repaso_quizz1.DTOs.LoginRequestDTO;
import com.example.repaso_quizz1.DTOs.RequestUserDTO;
import com.example.repaso_quizz1.DTOs.ResponseUserDTO;
import com.example.repaso_quizz1.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<ResponseUserDTO> register(@Valid @RequestBody RequestUserDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
