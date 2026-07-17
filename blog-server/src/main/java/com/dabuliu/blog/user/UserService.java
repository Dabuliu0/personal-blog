package com.dabuliu.blog.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(
            String username,
            String email,
            String rawPassword) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("邮箱不能为空");
        }

        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("密码不能为空");
        }

        String normalizedUsername = username.trim();
        String normalizedEmail = email.trim().toLowerCase();

        if (repository.existsByUsername(normalizedUsername)) {
            throw new IllegalArgumentException("用户名已经存在");
        }

        if (repository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("邮箱已经存在");
        }

        String passwordHash =
                passwordEncoder.encode(rawPassword);

        UserAccount user = new UserAccount(
                normalizedUsername,
                normalizedEmail,
                passwordHash);

        repository.save(user);
    }
}