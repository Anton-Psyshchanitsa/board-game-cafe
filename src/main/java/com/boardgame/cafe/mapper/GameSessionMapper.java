package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.GameSessionRequestDto;
import com.boardgame.cafe.dto.response.GameSessionResponseDto;
import com.boardgame.cafe.entity.GameSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GameSessionMapper {

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "isEdited", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    GameSession toEntity(GameSessionRequestDto dto);

    @Mapping(target = "bookingId", source = "booking.id")
    @Mapping(target = "gameName", source = "game.name")
    @Mapping(target = "username", source = "user.username")
    GameSessionResponseDto toResponseDto(GameSession gameSession);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "isEdited", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(GameSessionRequestDto dto, @MappingTarget GameSession gameSession);
}