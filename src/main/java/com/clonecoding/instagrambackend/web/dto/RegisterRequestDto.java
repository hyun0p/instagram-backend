package com.clonecoding.instagrambackend.web.dto;

import lombok.Getter;

@Getter
public class RegisterRequestDto {
    private String username;
    private String name;
    private String password;
    private String email;
}

