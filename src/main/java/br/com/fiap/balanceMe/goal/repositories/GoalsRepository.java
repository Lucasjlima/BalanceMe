package br.com.fiap.balanceMe.goal.repositories;

import br.com.fiap.balanceMe.goal.entity.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Long> {
}