package com.clonecoding.instagrambackend.mapper;

import com.clonecoding.instagrambackend.domain.Image;
import com.clonecoding.instagrambackend.web.dto.ImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto toDto(Image image);

    @Mapping(target = "post", ignore = true)
    Image toEntity(ImageDto imageDto);

    @Named("ImageDtoList")
    default List<ImageDto> toDtoList(List<Image> images) {
        return images
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
