package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.service.UserService;
import com.clonecoding.instagrambackend.web.dto.AuthenticationResponseDto;
import com.clonecoding.instagrambackend.web.dto.LoginRequestDto;
import com.clonecoding.instagrambackend.web.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequestDto requestDto) {
        this.userService.register(requestDto);
        return null;
    }

    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody LoginRequestDto requestDto) {
        return this.userService.login(requestDto);
    }

    @PostMapping("/logout")
    public User logout() {
        return null;
    }
}
