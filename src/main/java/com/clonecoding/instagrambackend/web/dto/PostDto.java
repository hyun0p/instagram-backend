package com.clonecoding.instagrambackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class PostDto {
    private Long id;
    private String username;
    private String text;
    private Long views;
    private List<ImageDto> images;
    private Long likes;
    private Long comments;
}
