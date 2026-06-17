package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.UserProfileRequestDto;
import com.boardgame.cafe.dto.response.UserProfileResponseDto;
import com.boardgame.cafe.entity.User;
import com.boardgame.cafe.entity.UserProfile;
import com.boardgame.cafe.mapper.UserProfileMapper;
import com.boardgame.cafe.repository.UserProfileRepository;
import com.boardgame.cafe.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    @Transactional
    public UserProfileResponseDto create(Long userId, UserProfileRequestDto dto) {
        if (userProfileRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("Profile already exists for user id: " + userId);
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        UserProfile profile = userProfileMapper.toEntity(dto);
        profile.setUser(user);
        return userProfileMapper.toResponseDto(userProfileRepository.save(profile));
    }

    @Transactional(readOnly = true)
    public UserProfileResponseDto findByUserId(Long userId) {
        return userProfileRepository.findByUserId(userId)
                .map(userProfileMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user id: " + userId));
    }

    @Transactional
    public UserProfileResponseDto update(Long userId, UserProfileRequestDto dto) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user id: " + userId));
        userProfileMapper.updateEntityFromDto(dto, profile);
        return userProfileMapper.toResponseDto(userProfileRepository.save(profile));
    }

    @Transactional
    public void delete(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user id: " + userId));
        userProfileRepository.delete(profile);
    }
}