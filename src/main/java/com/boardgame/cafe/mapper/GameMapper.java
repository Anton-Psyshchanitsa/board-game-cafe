package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.GameRequestDto;
import com.boardgame.cafe.dto.response.GameResponseDto;
import com.boardgame.cafe.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "gameInstances", ignore = true)
    @Mapping(target = "gameSessions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Game toEntity(GameRequestDto dto);

    @Mapping(target = "categoryName", source = "category.name")
    GameResponseDto toResponseDto(Game game);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "gameInstances", ignore = true)
    @Mapping(target = "gameSessions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(GameRequestDto dto, @MappingTarget Game game);
}