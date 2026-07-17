package com.dabuliu.blog.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepositoryAdapter implements UserRepository {

    private final SpringDataUserJpaRepository jpaRepository;

    public JpaUserRepositoryAdapter(
            SpringDataUserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(UserAccount user) {
        jpaRepository.save(user);
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return jpaRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmailIgnoreCase(email);
    }
}