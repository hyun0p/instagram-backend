package com.clonecoding.instagrambackend.mapper;

import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.domain.UserRepository;
import com.clonecoding.instagrambackend.web.dto.UserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "posts", expression = "java(userRepository.countPosts(user.getId()))")
    @Mapping(target = "followings", expression = "java(userRepository.countFollowings(user.getId()))")
    @Mapping(target = "followers", expression = "java(userRepository.countFollowers(user.getId()))")
    UserInfoDto toDto(User user, UserRepository userRepository);
}
