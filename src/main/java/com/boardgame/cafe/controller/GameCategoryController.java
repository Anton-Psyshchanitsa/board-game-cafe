package com.boardgame.cafe.controller;

import com.boardgame.cafe.dto.request.GameCategoryRequestDto;
import com.boardgame.cafe.dto.response.GameCategoryResponseDto;
import com.boardgame.cafe.service.GameCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-categories")
@RequiredArgsConstructor
public class GameCategoryController {

    private final GameCategoryService gameCategoryService;

    @PostMapping
    public ResponseEntity<GameCategoryResponseDto> create(@RequestBody GameCategoryRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameCategoryService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameCategoryResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gameCategoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GameCategoryResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(gameCategoryService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameCategoryResponseDto> update(
            @PathVariable Long id,
            @RequestBody GameCategoryRequestDto dto) {
        return ResponseEntity.ok(gameCategoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}