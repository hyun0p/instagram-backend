package com.clonecoding.instagrambackend.web.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private boolean nested;
    private String username;
    private String text;
    private Long postId;
    private Long parentId;
}
