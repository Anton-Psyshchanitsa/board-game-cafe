package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.RoleRequestDto;
import com.boardgame.cafe.dto.response.RoleResponseDto;
import com.boardgame.cafe.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleRequestDto dto);

    RoleResponseDto toResponseDto(Role role);

    void updateEntityFromDto(RoleRequestDto dto, @MappingTarget Role role);
}