package com.clonecoding.instagrambackend.service;

import com.clonecoding.instagrambackend.domain.*;
import com.clonecoding.instagrambackend.jwt.JwtTokenProvider;
import com.clonecoding.instagrambackend.web.dto.TokenDto;
import com.clonecoding.instagrambackend.web.dto.LoginDto;
import com.clonecoding.instagrambackend.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(UserDto userDto) {
        Role userRole = roleRepository.findByRole(Roletype.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error : ROLE_USER is not found"));
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .email(userDto.getEmail())
                .roles(Collections.singleton(userRole))
                .build();

        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
        userRepository.save(user);
    }

    public TokenDto login(LoginDto loginDto) {
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate( new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        return TokenDto.builder()
                .token(jwt)
                .build();
    }
}
