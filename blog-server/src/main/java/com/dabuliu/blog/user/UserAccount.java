package com.dabuliu.blog.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(
            name = "password_hash",
            nullable = false,
            length = 100)
    private String passwordHash;

    private LocalDateTime createdTime;

    public UserAccount() {
    }

    public UserAccount(
            String username,
            String email,
            String passwordHash) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("邮箱不能为空");
        }

        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("密码摘要不能为空");
        }

        this.username = username.trim();
        this.email = email.trim().toLowerCase();
        this.passwordHash = passwordHash;
        this.createdTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}