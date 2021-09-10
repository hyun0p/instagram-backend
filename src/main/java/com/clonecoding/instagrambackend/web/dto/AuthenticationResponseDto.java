package com.clonecoding.instagrambackend.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponseDto {

    private String token;
}
