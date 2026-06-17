package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.GameInstanceRequestDto;
import com.boardgame.cafe.dto.response.GameInstanceResponseDto;
import com.boardgame.cafe.entity.GameInstance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GameInstanceMapper {

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "bookingGames", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    GameInstance toEntity(GameInstanceRequestDto dto);

    @Mapping(target = "gameName", source = "game.name")
    GameInstanceResponseDto toResponseDto(GameInstance gameInstance);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "bookingGames", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(GameInstanceRequestDto dto, @MappingTarget GameInstance gameInstance);
}