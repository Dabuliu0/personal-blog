package com.dabuliu.blog.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//把数据库用户交给 Spring Security 做认证
@Service
public class BlogUserDetailsService
        implements UserDetailsService {
        //UserDetailsService是内部的

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