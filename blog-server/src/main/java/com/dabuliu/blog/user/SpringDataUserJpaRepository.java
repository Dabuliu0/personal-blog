package com.dabuliu.blog.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserJpaRepository
        extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);
}