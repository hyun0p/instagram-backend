package com.clonecoding.instagrambackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class PostRequestDto {
    private String username;
    private String text;
    private List<ImageDto> images;
}
