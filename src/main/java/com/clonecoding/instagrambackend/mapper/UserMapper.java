package com.clonecoding.instagrambackend.mapper;

import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.web.dto.UserInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserInfoDto toDto(User user);
}
