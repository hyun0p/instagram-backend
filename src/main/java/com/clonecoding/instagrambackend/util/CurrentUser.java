package com.clonecoding.instagrambackend.util;

import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class CurrentUser {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
    }
}
