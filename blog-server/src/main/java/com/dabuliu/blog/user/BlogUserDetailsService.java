package com.dabuliu.blog.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserDetailsService
        implements UserDetailsService {

    private final UserRepository repository;

    public BlogUserDetailsService(
            UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username) {

        UserAccount user = repository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "用户不存在"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .roles("USER")
                .build();
    }
}