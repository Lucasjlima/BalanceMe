package br.com.fiap.balanceMe.goal.repositories;

import br.com.fiap.balanceMe.goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalsRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long userId);
}