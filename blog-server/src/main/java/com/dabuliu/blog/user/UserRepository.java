package com.dabuliu.blog.user;

import java.util.Optional;

public interface UserRepository {

    void save(UserAccount user);

    Optional<UserAccount> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}