package br.com.fiap.balanceMe.user.repository;

import br.com.fiap.balanceMe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}