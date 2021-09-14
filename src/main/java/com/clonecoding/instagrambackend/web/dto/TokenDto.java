package com.clonecoding.instagrambackend.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDto {
    private String token;
}
