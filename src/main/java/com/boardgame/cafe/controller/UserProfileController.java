package com.boardgame.cafe.controller;

import com.boardgame.cafe.dto.request.UserProfileRequestDto;
import com.boardgame.cafe.dto.response.UserProfileResponseDto;
import com.boardgame.cafe.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfileResponseDto> create(
            @PathVariable Long userId,
            @RequestBody UserProfileRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileService.create(userId, dto));
    }

    @GetMapping
    public ResponseEntity<UserProfileResponseDto> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userProfileService.findByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<UserProfileResponseDto> update(
            @PathVariable Long userId,
            @RequestBody UserProfileRequestDto dto) {
        return ResponseEntity.ok(userProfileService.update(userId, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userProfileService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}