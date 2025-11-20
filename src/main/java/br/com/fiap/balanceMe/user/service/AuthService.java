package br.com.fiap.balanceMe.user.service;

import br.com.fiap.balanceMe.security.service.JwtService;
import br.com.fiap.balanceMe.user.dto.request.LoginRequest;
import br.com.fiap.balanceMe.user.dto.request.RegisterRequest;
import br.com.fiap.balanceMe.user.dto.response.AuthResponse;
import br.com.fiap.balanceMe.user.entity.User;
import br.com.fiap.balanceMe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(User user) {
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userEmail(),
                        request.password()
                )
        );

        User user = userRepository.findByUserEmail(request.userEmail()).orElseThrow();

        return new AuthResponse(jwtService.generateToken(user));
    }
}
