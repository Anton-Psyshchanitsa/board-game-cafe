package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.BookingRequestDto;
import com.boardgame.cafe.dto.response.BookingResponseDto;
import com.boardgame.cafe.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {GameInstanceMapper.class})
public interface BookingMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "bookingGames", ignore = true)
    @Mapping(target = "gameSessions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Booking toEntity(BookingRequestDto dto);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "tableNumber", source = "table.tableNumber")
    @Mapping(target = "gameInstances", source = "bookingGames")
    BookingResponseDto toResponseDto(Booking booking);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "bookingGames", ignore = true)
    @Mapping(target = "gameSessions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(BookingRequestDto dto, @MappingTarget Booking booking);
}