package com.boardgame.cafe.dto.response;

import com.boardgame.cafe.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponseDto {

    private Long id;

    private String username;

    @JsonProperty("table_number")
    private String tableNumber;

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

    private BookingStatus status;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;

    @JsonProperty("special_requests")
    private String specialRequests;

    @JsonProperty("game_instances")
    private List<GameInstanceResponseDto> gameInstances;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}