package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.FollowService;
import com.clonecoding.instagrambackend.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{username}")
    public ResponseEntity<Void> follow(@PathVariable String username) {
        followService.follow(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> unfollow(@PathVariable String username) {
        followService.unfollow(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<List<UserInfoDto>> getFollowers(@PathVariable String username) {
        return new ResponseEntity<>(followService.getFollowers(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/followings")
    public ResponseEntity<List<UserInfoDto>> getFollowings(@PathVariable String username) {
        return new ResponseEntity<>(followService.getFollowings(username), HttpStatus.OK);
    }
}
