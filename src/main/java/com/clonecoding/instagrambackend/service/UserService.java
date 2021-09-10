package com.clonecoding.instagrambackend.service;

import com.clonecoding.instagrambackend.domain.Role;
import com.clonecoding.instagrambackend.domain.RoleRepository;
import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.domain.UserRepository;
import com.clonecoding.instagrambackend.jwt.JwtTokenProvider;
import com.clonecoding.instagrambackend.web.dto.AuthenticationResponseDto;
import com.clonecoding.instagrambackend.web.dto.LoginRequestDto;
import com.clonecoding.instagrambackend.web.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequestDto requestDto) {
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error : ROLE_USER is not found"));
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .roles(Collections.singleton(userRole))
                .build();

        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
        userRepository.save(user);
    }

    public AuthenticationResponseDto login(LoginRequestDto requestDto) {
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate( new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        return AuthenticationResponseDto.builder()
                .token(jwt)
                .build();
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }

}
