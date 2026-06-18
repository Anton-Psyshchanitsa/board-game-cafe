package com.boardgame.cafe.controller;

import com.boardgame.cafe.dto.request.GameSessionRequestDto;
import com.boardgame.cafe.dto.response.GameSessionResponseDto;
import com.boardgame.cafe.service.GameSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-sessions")
@RequiredArgsConstructor
public class GameSessionController {

    private final GameSessionService gameSessionService;

    @PostMapping
    public ResponseEntity<GameSessionResponseDto> create(@RequestBody GameSessionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameSessionService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameSessionResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gameSessionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GameSessionResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameSessionService.findAll(pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<GameSessionResponseDto>> findByUserId(
            @PathVariable Long userId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameSessionService.findByUserId(userId, pageable));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Page<GameSessionResponseDto>> findByGameId(
            @PathVariable Long gameId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameSessionService.findByGameId(gameId, pageable));
    }

    @GetMapping("/game/{gameId}/rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long gameId) {
        return ResponseEntity.ok(gameSessionService.getAverageRating(gameId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameSessionResponseDto> update(
            @PathVariable Long id,
            @RequestBody GameSessionRequestDto dto) {
        return ResponseEntity.ok(gameSessionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameSessionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}