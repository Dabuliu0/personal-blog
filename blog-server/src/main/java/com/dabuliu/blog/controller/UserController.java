package com.dabuliu.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dabuliu.blog.user.LoginRequest;
import com.dabuliu.blog.user.LoginResponse;
import com.dabuliu.blog.user.LoginService;
import com.dabuliu.blog.user.RegisterUserRequest;
import com.dabuliu.blog.user.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    public UserController(
            UserService userService,
            LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @PostMapping("/users/register")
    public void register(
            @Valid @RequestBody RegisterUserRequest request) {

        userService.register(
                request.username(),
                request.email(),
                request.password());
    }

    @PostMapping("/users/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        String token = loginService.login(
                request.username(),
                request.password());

        return new LoginResponse(token);
    }
}