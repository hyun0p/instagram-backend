package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.UserService;
import com.clonecoding.instagrambackend.web.dto.TokenDto;
import com.clonecoding.instagrambackend.web.dto.LoginDto;
import com.clonecoding.instagrambackend.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDto userDto) {
        this.userService.register(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto requestDto) {
        return new ResponseEntity<>(this.userService.login(requestDto), HttpStatus.OK);
    }

}
