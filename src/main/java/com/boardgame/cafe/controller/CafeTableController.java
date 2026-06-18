package com.boardgame.cafe.controller;

import com.boardgame.cafe.dto.request.CafeTableRequestDto;
import com.boardgame.cafe.dto.response.CafeTableResponseDto;
import com.boardgame.cafe.service.CafeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class CafeTableController {

    private final CafeTableService cafeTableService;

    @PostMapping
    public ResponseEntity<CafeTableResponseDto> create(@RequestBody CafeTableRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cafeTableService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CafeTableResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cafeTableService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CafeTableResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(cafeTableService.findAll(pageable));
    }

    @GetMapping("/active")
    public ResponseEntity<Page<CafeTableResponseDto>> findActive(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(cafeTableService.findActive(pageable));
    }

    @GetMapping("/capacity")
    public ResponseEntity<Page<CafeTableResponseDto>> findByCapacity(
            @RequestParam Integer min,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(cafeTableService.findByCapacity(min, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CafeTableResponseDto> update(
            @PathVariable Long id,
            @RequestBody CafeTableRequestDto dto) {
        return ResponseEntity.ok(cafeTableService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cafeTableService.delete(id);
        return ResponseEntity.noContent().build();
    }
}