package br.com.fiap.balanceMe.user.controller;

import br.com.fiap.balanceMe.user.dto.request.LoginRequest;
import br.com.fiap.balanceMe.user.dto.request.RegisterRequest;
import br.com.fiap.balanceMe.user.dto.response.AuthResponse;
import br.com.fiap.balanceMe.user.mapper.UserMapper;
import br.com.fiap.balanceMe.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.register(UserMapper.toUser(request)));
    }

    @PostMapping("register/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.register(UserMapper.toAdmin(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.login(request));
    }


}
