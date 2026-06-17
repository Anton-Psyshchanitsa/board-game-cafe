package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.GameRequestDto;
import com.boardgame.cafe.dto.response.GameResponseDto;
import com.boardgame.cafe.entity.Game;
import com.boardgame.cafe.entity.GameCategory;
import com.boardgame.cafe.enums.DifficultyLevel;
import com.boardgame.cafe.mapper.GameMapper;
import com.boardgame.cafe.repository.GameCategoryRepository;
import com.boardgame.cafe.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameCategoryRepository gameCategoryRepository;
    private final GameMapper gameMapper;

    @Transactional
    public GameResponseDto create(GameRequestDto dto) {
        GameCategory category = gameCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + dto.getCategoryId()));
        Game game = gameMapper.toEntity(dto);
        game.setCategory(category);
        return gameMapper.toResponseDto(gameRepository.save(game));
    }

    @Transactional(readOnly = true)
    public GameResponseDto findById(Long id) {
        return gameMapper.toResponseDto(getById(id));
    }

    @Transactional(readOnly = true)
    public Page<GameResponseDto> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable).map(gameMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameResponseDto> findByCategoryId(Long categoryId, Pageable pageable) {
        return gameRepository.findByCategoryId(categoryId, pageable).map(gameMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameResponseDto> findByDifficultyLevel(DifficultyLevel level, Pageable pageable) {
        return gameRepository.findByDifficultyLevel(level, pageable).map(gameMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameResponseDto> findByName(String name, Pageable pageable) {
        return gameRepository.findByNameContainingIgnoreCase(name, pageable).map(gameMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameResponseDto> findByPlayerCount(Integer players, Pageable pageable) {
        return gameRepository.findByPlayerCount(players, pageable).map(gameMapper::toResponseDto);
    }

    @Transactional
    public GameResponseDto update(Long id, GameRequestDto dto) {
        Game game = getById(id);
        if (dto.getCategoryId() != null) {
            GameCategory category = gameCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + dto.getCategoryId()));
            game.setCategory(category);
        }
        gameMapper.updateEntityFromDto(dto, game);
        return gameMapper.toResponseDto(gameRepository.save(game));
    }

    @Transactional
    public void delete(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new EntityNotFoundException("Game not found with id: " + id);
        }
        gameRepository.deleteById(id);
    }

    private Game getById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + id));
    }
}