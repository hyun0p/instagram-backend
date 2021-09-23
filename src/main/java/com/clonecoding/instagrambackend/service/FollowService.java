package com.clonecoding.instagrambackend.service;

import com.clonecoding.instagrambackend.domain.Follow;
import com.clonecoding.instagrambackend.domain.FollowRepository;
import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.domain.UserRepository;
import com.clonecoding.instagrambackend.mapper.UserMapper;
import com.clonecoding.instagrambackend.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final UserMapper userMapper;

    @Transactional
    public void follow(String username) {
        User user = getCurrentUser();
        User followee = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        validationCheck(user, followee);
        Follow follow = new Follow(user, followee);
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(String username) {
        User user = getCurrentUser();
        User followee = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        Follow follow = followRepository.findByFollowerAndFollowee(user, followee)
                .orElseThrow(() -> new RuntimeException("Error : Follow is not found"));
        followRepository.delete(follow);
    }

    @Transactional
    public List<UserInfoDto> getFollowings(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        List<Follow> followings = followRepository.findByFollower(user);
        return followings.stream()
                .map(follow -> userMapper.toDto(follow.getFollowee(), userRepository))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<UserInfoDto> getFollowers(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        List<Follow> followers = followRepository.findByFollowee(user);
        return followers.stream()
                .map(follow -> userMapper.toDto(follow.getFollower(), userRepository))
                .collect(Collectors.toList());
    }

    private void validationCheck(User user, User followee) {
        if (user == followee) {
            throw new RuntimeException("Error : Cannot follow");
        }
        followRepository.findByFollowerAndFollowee(user, followee)
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 팔로우 되었습니다.");
                });
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
    }
}
