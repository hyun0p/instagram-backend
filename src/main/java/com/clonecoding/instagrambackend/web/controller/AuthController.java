package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.AuthService;
import com.clonecoding.instagrambackend.web.dto.LoginDto;
import com.clonecoding.instagrambackend.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto requestDto) {
        return new ResponseEntity<>(this.authService.login(requestDto), HttpStatus.OK);
    }
}
