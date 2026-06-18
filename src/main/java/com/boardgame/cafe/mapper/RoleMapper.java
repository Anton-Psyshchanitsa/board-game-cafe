package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.RoleRequestDto;
import com.boardgame.cafe.dto.response.RoleResponseDto;
import com.boardgame.cafe.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleRequestDto dto);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    RoleResponseDto toResponseDto(Role role);

    void updateEntityFromDto(RoleRequestDto dto, @MappingTarget Role role);
}