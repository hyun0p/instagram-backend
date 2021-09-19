package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.UserService;
import com.clonecoding.instagrambackend.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDto userDto) {
        this.userService.register(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
