package com.clonecoding.instagrambackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ImageDto {
    private String url;
    private String type;
}
