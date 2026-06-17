package com.boardgame.cafe.mapper;

import com.boardgame.cafe.dto.request.CafeTableRequestDto;
import com.boardgame.cafe.dto.response.CafeTableResponseDto;
import com.boardgame.cafe.entity.CafeTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CafeTableMapper {

    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CafeTable toEntity(CafeTableRequestDto dto);

    CafeTableResponseDto toResponseDto(CafeTable cafeTable);

    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(CafeTableRequestDto dto, @MappingTarget CafeTable cafeTable);
}