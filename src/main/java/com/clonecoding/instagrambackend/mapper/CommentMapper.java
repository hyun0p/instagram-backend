package com.clonecoding.instagrambackend.mapper;

import com.clonecoding.instagrambackend.domain.Comment;
import com.clonecoding.instagrambackend.domain.Post;
import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.web.dto.CommentDto;
import com.clonecoding.instagrambackend.web.dto.CommentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "parentId", ignore = true)
    @Mapping(target = "childrenSize", expression = "java(Long.valueOf(comment.getChildren().size()))")
    CommentDto postCommentToDto(Comment comment);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "childrenSize", expression = "java(Long.valueOf(comment.getChildren().size()))")
    CommentDto nestedCommentToDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "text", source = "commentRequestDto.text")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "parent", source = "parent")
    Comment toEntity(CommentRequestDto commentRequestDto, User user, Post post, Comment parent);

}
