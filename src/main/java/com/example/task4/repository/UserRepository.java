package com.example.task4.repository;

import com.example.task4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);
    boolean existsByLogin(String login);
    List<User> findAllByLogin(String login);
}