package com.boardgame.cafe.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CafeTableRequestDto {

    @JsonProperty("table_number")
    private String tableNumber;

    private Integer capacity;

    private String location;

    @JsonProperty("is_active")
    private Boolean isActive;
}