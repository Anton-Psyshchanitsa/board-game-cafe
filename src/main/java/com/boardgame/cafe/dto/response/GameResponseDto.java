package com.boardgame.cafe.dto.response;

import com.boardgame.cafe.enums.DifficultyLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameResponseDto {

    private Long id;

    private String name;

    private String description;

    @JsonProperty("category_name")
    private String categoryName;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}