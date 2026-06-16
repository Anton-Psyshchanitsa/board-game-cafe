package com.boardgame.cafe.dto.request;

import com.boardgame.cafe.enums.GameCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameInstanceRequestDto {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("inventory_number")
    private String inventoryNumber;

    private GameCondition condition;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;

    @JsonProperty("is_available")
    private Boolean isAvailable;

    private String notes;
}