package com.boardgame.cafe.controller;

import com.boardgame.cafe.dto.request.GameRequestDto;
import com.boardgame.cafe.dto.response.GameResponseDto;
import com.boardgame.cafe.enums.DifficultyLevel;
import com.boardgame.cafe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameResponseDto> create(@RequestBody GameRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GameResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameService.findAll(pageable));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<GameResponseDto>> findByCategoryId(
            @PathVariable Long categoryId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameService.findByCategoryId(categoryId, pageable));
    }

    @GetMapping("/difficulty/{level}")
    public ResponseEntity<Page<GameResponseDto>> findByDifficultyLevel(
            @PathVariable DifficultyLevel level,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameService.findByDifficultyLevel(level, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<GameResponseDto>> findByName(
            @RequestParam String name,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameService.findByName(name, pageable));
    }

    @GetMapping("/players")
    public ResponseEntity<Page<GameResponseDto>> findByPlayerCount(
            @RequestParam Integer count,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameService.findByPlayerCount(count, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDto> update(
            @PathVariable Long id,
            @RequestBody GameRequestDto dto) {
        return ResponseEntity.ok(gameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}