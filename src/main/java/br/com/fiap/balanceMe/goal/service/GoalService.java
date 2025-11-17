package br.com.fiap.balanceMe.goal.service;

import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.goal.repositories.GoalsRepository;
import br.com.fiap.balanceMe.user.entity.User;
import br.com.fiap.balanceMe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalsRepository repository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Goal> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Goal create(Goal goal) {
        User user = userRepository.getReferenceById(goal.getUser().getId());
        goal.setUser(user);
        return  repository.save(goal);
    }
}
