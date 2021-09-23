package com.clonecoding.instagrambackend.mapper;

import com.clonecoding.instagrambackend.domain.Image;
import com.clonecoding.instagrambackend.domain.Post;
import com.clonecoding.instagrambackend.domain.PostRepository;
import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.web.dto.PostDto;
import com.clonecoding.instagrambackend.web.dto.PostRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = ImageMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "images", source = "images", qualifiedByName = "ImageDtoList")
    @Mapping(target = "comments", expression = "java(postRepository.countComments(post.getId())")
    @Mapping(target = "likes", expression = "java(postRepository.countLikes(post.getId())")
    PostDto toDto(Post post, PostRepository postRepository);

    @Mapping(target = "text", source = "postRequestDto.text")
    @Mapping(target = "user", source = "user")
    Post toEntity(PostRequestDto postRequestDto, User user, List<Image> images);
}
