package com.clonecoding.instagrambackend.mapper;

import com.clonecoding.instagrambackend.domain.Image;
import com.clonecoding.instagrambackend.domain.Post;
import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.web.dto.PostDto;
import com.clonecoding.instagrambackend.web.dto.PostRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ImageMapper.class)
public interface PostMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "images", source = "images", qualifiedByName = "ImageDtoList")
    PostDto toDto(Post post);

    Post toEntity(PostRequestDto postRequestDto, User user, List<Image> images);
}
