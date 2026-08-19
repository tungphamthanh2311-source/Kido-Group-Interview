package com.example.interview.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    // Spring Data JPA tu sinh query theo ten method: tim user bang username.
    Optional<AppUser> findByUsername(String username);
}
