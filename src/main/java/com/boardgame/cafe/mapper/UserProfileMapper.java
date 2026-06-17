package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.UserProfileRequestDto;
import com.boardgame.cafe.dto.response.UserProfileResponseDto;
import com.boardgame.cafe.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    UserProfile toEntity(UserProfileRequestDto dto);

    UserProfileResponseDto toResponseDto(UserProfile userProfile);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDto(UserProfileRequestDto dto, @MappingTarget UserProfile userProfile);
}