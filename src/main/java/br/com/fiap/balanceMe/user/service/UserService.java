package br.com.fiap.balanceMe.user.service;

import br.com.fiap.balanceMe.config.SecurityConfig;
import br.com.fiap.balanceMe.user.dto.request.UserUpdateRequest;
import br.com.fiap.balanceMe.user.entity.User;
import br.com.fiap.balanceMe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Optional<User> edit(UserUpdateRequest request, Long userId) {
        Optional<User> optUser = repository.findById(userId);
        if(optUser.isPresent()) {
            User user = optUser.get();

            if(request.username() != null) {
                user.setUsername(request.username());
            }
            if(request.userEmail() != null) {
                user.setUserEmail(request.userEmail());
            }
            if(request.timezone() != null) {
                user.setTimezone(request.timezone());
            }
            if(request.role() != null) {
                user.setRole(request.role());
            }
            repository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
