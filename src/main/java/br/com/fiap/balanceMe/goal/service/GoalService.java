package br.com.fiap.balanceMe.goal.service;

import br.com.fiap.balanceMe.goal.dto.request.GoalUpdateRequest;
import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.goal.entity.Status;
import br.com.fiap.balanceMe.goal.repositories.GoalsRepository;
import br.com.fiap.balanceMe.user.entity.User;
import br.com.fiap.balanceMe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalsRepository repository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Goal> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Goal> findAllGoalsByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Transactional
    public Goal create(Goal goal) {
        User user = userRepository.getReferenceById(goal.getUser().getId());
        goal.setUser(user);
        goal.setStatus(Status.ACTIVE);
        return repository.save(goal);
    }

    @Transactional(readOnly = true)
    public Page<Goal> pagedGoals(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Optional<Goal> completeGoal(Long goalId) {
        Optional<Goal> optGoal = repository.findById(goalId);
        if (optGoal.isPresent()) {
            Goal goal = optGoal.get();
            goal.setIsActive(false);
            goal.setStatus(Status.COMPLETED);
            goal.setCompletedAt(LocalDateTime.now());
            repository.save(goal);
            return Optional.of(goal);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Goal> edit(GoalUpdateRequest request, Long goalId) {
        Optional<Goal> optGoal = repository.findById(goalId);
        if (optGoal.isPresent() && optGoal.get().getIsActive().equals(true)) {
            Goal goal = optGoal.get();

            if (request.category() != null) {
                goal.setCategory(request.category());
            }

            if (request.frequency() != null) {
                goal.setFrequency(request.frequency());
            }

            if (request.status() != null) {
                if (request.status().equals(Status.CANCELLED)) {
                    goal.setStatus(Status.CANCELLED);
                    goal.setIsActive(false);
                }
                goal.setStatus(request.status());
            }

            if (request.unitMeasure() != null) {
                goal.setUnitMeasure(request.unitMeasure());
            }

            if (request.isActive() != null) {
                goal.setIsActive(request.isActive());
            }

            if (request.startDate() != null) {
                goal.setStartDate(request.startDate());
            }

            if (request.endDate() != null) {
                goal.setEndDate(request.endDate());
            }
            repository.save(goal);
            return Optional.of(goal);
        }
        return Optional.empty();
    }

    @Transactional
    public void delete(Long id) {
        Optional<Goal> optGoal = repository.findById(id);
        if (optGoal.isPresent()) {
            Goal goal = optGoal.get();
            goal.setIsActive(false);
            goal.setStatus(Status.CANCELLED);
            goal.setEndDate(LocalDateTime.now().toLocalDate());
            repository.save(goal);
        }
    }
}
