package com.clonecoding.instagrambackend.service;

import com.clonecoding.instagrambackend.jwt.JwtTokenProvider;
import com.clonecoding.instagrambackend.web.dto.LoginDto;
import com.clonecoding.instagrambackend.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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
