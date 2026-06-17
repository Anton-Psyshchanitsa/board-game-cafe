package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.GameCategoryRequestDto;
import com.boardgame.cafe.dto.response.GameCategoryResponseDto;
import com.boardgame.cafe.entity.GameCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GameCategoryMapper {

    GameCategory toEntity(GameCategoryRequestDto dto);

    GameCategoryResponseDto toResponseDto(GameCategory gameCategory);

    void updateEntityFromDto(GameCategoryRequestDto dto, @MappingTarget GameCategory gameCategory);
}