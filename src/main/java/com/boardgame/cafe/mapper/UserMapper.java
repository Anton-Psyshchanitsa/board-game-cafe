package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.UserRequestDto;
import com.boardgame.cafe.dto.response.UserResponseDto;
import com.boardgame.cafe.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserProfileMapper.class})
public interface UserMapper {

    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "gameSessions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequestDto dto);

    @Mapping(target = "roleName", source = "role.name")
    @Mapping(target = "profile", source = "userProfile")
    UserResponseDto toResponseDto(User user);

    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "gameSessions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(UserRequestDto dto, @MappingTarget User user);
}