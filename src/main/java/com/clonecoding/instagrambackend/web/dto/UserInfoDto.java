package com.clonecoding.instagrambackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserInfoDto {
    private String username;
    private String name;
    private String email;
    private String text;
    private String image;
    private Long posts;
    private Long followings;
    private Long followers;
}
