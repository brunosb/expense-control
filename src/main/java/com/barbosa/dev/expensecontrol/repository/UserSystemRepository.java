package com.barbosa.dev.expensecontrol.repository;

import com.barbosa.dev.expensecontrol.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {

    Optional<UserSystem> findByEmailOrLogin(String email, String login);

    Boolean existsByLogin(String login);
}
