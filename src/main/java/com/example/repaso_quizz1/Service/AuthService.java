package com.example.repaso_quizz1.Service;

import com.example.repaso_quizz1.Auth.TokenResponse;
import com.example.repaso_quizz1.DTOs.LoginRequestDTO;
import com.example.repaso_quizz1.DTOs.RequestUserDTO;
import com.example.repaso_quizz1.DTOs.ResponseUserDTO;
import com.example.repaso_quizz1.Model.User;
import com.example.repaso_quizz1.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseUserDTO register(RequestUserDTO request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El email ya existe");
        }
        User savedUser = userRepository.save(user);
        return new ResponseUserDTO(savedUser.getId());
    }

    public TokenResponse login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail());
        String token = jwtService.generateToken(user);
        return new TokenResponse(token);
    }
}
