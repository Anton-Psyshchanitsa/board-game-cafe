package com.boardgame.cafe.dto.request;

import com.boardgame.cafe.enums.DifficultyLevel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameRequestDto {

    private String name;

    private String description;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("min_players")
    private Integer minPlayers;

    @JsonProperty("max_players")
    private Integer maxPlayers;

    @JsonProperty("avg_duration_minutes")
    private Integer avgDurationMinutes;

    @JsonProperty("difficulty_level")
    private DifficultyLevel difficultyLevel;

    @JsonProperty("year_published")
    private Integer yearPublished;

    private String publisher;

    @JsonProperty("image_url")
    private String imageUrl;
}