package com.boardgame.cafe.dto.response;

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
public class GameSessionResponseDto {

    private Long id;

    @JsonProperty("booking_id")
    private Long bookingId;

    @JsonProperty("game_name")
    private String gameName;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("session_date")
    private LocalDate sessionDate;

    @JsonProperty("duration_minutes")
    private Integer durationMinutes;

    private Integer rating;

    private String review;

    @JsonProperty("is_edited")
    private Boolean isEdited;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}