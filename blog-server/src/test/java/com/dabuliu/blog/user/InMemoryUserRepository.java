package com.dabuliu.blog.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class InMemoryUserRepository implements UserRepository {

    private final List<UserAccount> users = new ArrayList<>();

    @Override
    public void save(UserAccount user) {
        users.add(user);
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername()
                        .equalsIgnoreCase(username))
                .findFirst();
    }

    @Override
    public boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail()
                        .equalsIgnoreCase(email));
    }
}