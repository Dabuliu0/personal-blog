package com.dabuliu.blog.user;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {

    @Test
    void shouldEncodePasswordWhenRegistering() {
        UserRepository repository =
                new InMemoryUserRepository();

        PasswordEncoder passwordEncoder =
                new BCryptPasswordEncoder();

        UserService service =
                new UserService(repository, passwordEncoder);

        service.register(
                "dabuliu",
                "dabuliu@example.com",
                "password123");

        UserAccount savedUser = repository
                .findByUsername("dabuliu")
                .orElseThrow();

        assertNotEquals(
                "password123",
                savedUser.getPasswordHash());

        assertTrue(passwordEncoder.matches(
                "password123",
                savedUser.getPasswordHash()));
    }

    @Test
    void shouldRejectDuplicateUsernameIgnoringCase() {
        UserRepository repository =
                new InMemoryUserRepository();

        UserService service = new UserService(
                repository,
                new BCryptPasswordEncoder());

        service.register(
                "Dabuliu",
                "first@example.com",
                "password123");

        assertThrows(
                IllegalArgumentException.class,
                () -> service.register(
                        "dabuliu",
                        "second@example.com",
                        "password456"));
    }

    @Test
    void shouldRejectDuplicateEmailIgnoringCase() {
        UserRepository repository =
                new InMemoryUserRepository();

        UserService service = new UserService(
                repository,
                new BCryptPasswordEncoder());

        service.register(
                "firstUser",
                "User@example.com",
                "password123");

        assertThrows(
                IllegalArgumentException.class,
                () -> service.register(
                        "secondUser",
                        "user@example.com",
                        "password456"));
    }
}