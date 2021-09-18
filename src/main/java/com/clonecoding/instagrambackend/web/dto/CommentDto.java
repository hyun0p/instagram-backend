package com.clonecoding.instagrambackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CommentDto {
    private Long id;
    private String username;
    private String text;
    private Long postId;
    private Long parentId;
    private Long childrenSize;
}
