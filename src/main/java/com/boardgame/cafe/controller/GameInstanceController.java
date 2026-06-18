package com.boardgame.cafe.controller;

import com.boardgame.cafe.dto.request.GameInstanceRequestDto;
import com.boardgame.cafe.dto.response.GameInstanceResponseDto;
import com.boardgame.cafe.enums.GameCondition;
import com.boardgame.cafe.service.GameInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-instances")
@RequiredArgsConstructor
public class GameInstanceController {

    private final GameInstanceService gameInstanceService;

    @PostMapping
    public ResponseEntity<GameInstanceResponseDto> create(@RequestBody GameInstanceRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameInstanceService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameInstanceResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gameInstanceService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GameInstanceResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameInstanceService.findAll(pageable));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Page<GameInstanceResponseDto>> findByGameId(
            @PathVariable Long gameId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameInstanceService.findByGameId(gameId, pageable));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<GameInstanceResponseDto>> findAvailable(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameInstanceService.findAvailable(pageable));
    }

    @GetMapping("/condition/{condition}")
    public ResponseEntity<Page<GameInstanceResponseDto>> findByCondition(
            @PathVariable GameCondition condition,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameInstanceService.findByCondition(condition, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameInstanceResponseDto> update(
            @PathVariable Long id,
            @RequestBody GameInstanceRequestDto dto) {
        return ResponseEntity.ok(gameInstanceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameInstanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}