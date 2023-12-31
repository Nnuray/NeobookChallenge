package com.example.neobookchallenge.repository;

import com.example.neobookchallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // идентификатор пользовательского класа

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
