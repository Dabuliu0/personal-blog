package com.dabuliu.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dabuliu.blog.user.RegisterUserRequest;
import com.dabuliu.blog.user.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users/register")
    public void register(
            @Valid @RequestBody RegisterUserRequest request) {

        service.register(
                request.username(),
                request.email(),
                request.password());
    }
}