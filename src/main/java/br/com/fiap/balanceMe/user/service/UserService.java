package br.com.fiap.balanceMe.user.service;

import br.com.fiap.balanceMe.config.SecurityConfig;
import br.com.fiap.balanceMe.user.entity.User;
import br.com.fiap.balanceMe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final SecurityConfig securityConfig;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public User create(User user) {
        user.setIsActive(true);
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }
}
