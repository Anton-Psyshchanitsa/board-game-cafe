package com.boardgame.cafe.dto.response;

import com.boardgame.cafe.enums.GameCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameInstanceResponseDto {

    private Long id;

    @JsonProperty("game_name")
    private String gameName;

    @JsonProperty("inventory_number")
    private String inventoryNumber;

    private GameCondition condition;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;

    @JsonProperty("is_available")
    private Boolean isAvailable;

    private String notes;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}