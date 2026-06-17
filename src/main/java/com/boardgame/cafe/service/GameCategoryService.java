package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.GameCategoryRequestDto;
import com.boardgame.cafe.dto.response.GameCategoryResponseDto;
import com.boardgame.cafe.entity.GameCategory;
import com.boardgame.cafe.mapper.GameCategoryMapper;
import com.boardgame.cafe.repository.GameCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameCategoryService {

    private final GameCategoryRepository gameCategoryRepository;
    private final GameCategoryMapper gameCategoryMapper;

    @Transactional
    public GameCategoryResponseDto create(GameCategoryRequestDto dto) {
        if (gameCategoryRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Category already exists with name: " + dto.getName());
        }
        GameCategory category = gameCategoryMapper.toEntity(dto);
        return gameCategoryMapper.toResponseDto(gameCategoryRepository.save(category));
    }

    @Transactional(readOnly = true)
    public GameCategoryResponseDto findById(Long id) {
        return gameCategoryMapper.toResponseDto(getById(id));
    }

    @Transactional(readOnly = true)
    public Page<GameCategoryResponseDto> findAll(Pageable pageable) {
        return gameCategoryRepository.findAll(pageable).map(gameCategoryMapper::toResponseDto);
    }

    @Transactional
    public GameCategoryResponseDto update(Long id, GameCategoryRequestDto dto) {
        GameCategory category = getById(id);
        gameCategoryMapper.updateEntityFromDto(dto, category);
        return gameCategoryMapper.toResponseDto(gameCategoryRepository.save(category));
    }

    @Transactional
    public void delete(Long id) {
        if (!gameCategoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with id: " + id);
        }
        gameCategoryRepository.deleteById(id);
    }

    private GameCategory getById(Long id) {
        return gameCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }
}