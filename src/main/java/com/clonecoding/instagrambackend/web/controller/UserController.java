package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.UserService;
import com.clonecoding.instagrambackend.web.dto.UserDto;
import com.clonecoding.instagrambackend.web.dto.UserInfoDto;
import lombok.Getter;
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
        userService.register(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<Void> update(@PathVariable String username, @RequestBody UserInfoDto userInfoDto) {
        userService.update(username, userInfoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        userService.delete(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserInfo(username), HttpStatus.OK);
    }

}
