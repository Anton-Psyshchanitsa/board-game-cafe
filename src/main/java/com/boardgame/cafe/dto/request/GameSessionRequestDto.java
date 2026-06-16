package com.boardgame.cafe.dto.request;

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
public class GameSessionRequestDto {

    @JsonProperty("booking_id")
    private Long bookingId;

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("session_date")
    private LocalDate sessionDate;

    @JsonProperty("duration_minutes")
    private Integer durationMinutes;

    private Integer rating;

    private String review;
}