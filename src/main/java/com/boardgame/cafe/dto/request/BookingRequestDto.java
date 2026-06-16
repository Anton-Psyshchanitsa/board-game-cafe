package com.boardgame.cafe.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("table_id")
    private Long tableId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("booking_date")
    private LocalDate bookingDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("start_time")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("end_time")
    private LocalTime endTime;

    @JsonProperty("number_of_players")
    private Integer numberOfPlayers;

    @JsonProperty("special_requests")
    private String specialRequests;

    @JsonProperty("game_instance_ids")
    private List<Long> gameInstanceIds;
}