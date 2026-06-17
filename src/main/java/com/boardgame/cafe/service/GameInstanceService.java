package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.GameInstanceRequestDto;
import com.boardgame.cafe.dto.response.GameInstanceResponseDto;
import com.boardgame.cafe.entity.Game;
import com.boardgame.cafe.entity.GameInstance;
import com.boardgame.cafe.enums.GameCondition;
import com.boardgame.cafe.mapper.GameInstanceMapper;
import com.boardgame.cafe.repository.GameInstanceRepository;
import com.boardgame.cafe.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameInstanceService {

    private final GameInstanceRepository gameInstanceRepository;
    private final GameRepository gameRepository;
    private final GameInstanceMapper gameInstanceMapper;

    @Transactional
    public GameInstanceResponseDto create(GameInstanceRequestDto dto) {
        if (gameInstanceRepository.existsByInventoryNumber(dto.getInventoryNumber())) {
            throw new IllegalArgumentException("Inventory number already exists: " + dto.getInventoryNumber());
        }
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + dto.getGameId()));
        GameInstance instance = gameInstanceMapper.toEntity(dto);
        instance.setGame(game);
        return gameInstanceMapper.toResponseDto(gameInstanceRepository.save(instance));
    }

    @Transactional(readOnly = true)
    public GameInstanceResponseDto findById(Long id) {
        return gameInstanceMapper.toResponseDto(getById(id));
    }

    @Transactional(readOnly = true)
    public Page<GameInstanceResponseDto> findAll(Pageable pageable) {
        return gameInstanceRepository.findAll(pageable).map(gameInstanceMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameInstanceResponseDto> findByGameId(Long gameId, Pageable pageable) {
        return gameInstanceRepository.findByGameId(gameId, pageable).map(gameInstanceMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameInstanceResponseDto> findAvailable(Pageable pageable) {
        return gameInstanceRepository.findByIsAvailableTrue(pageable).map(gameInstanceMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameInstanceResponseDto> findByCondition(GameCondition condition, Pageable pageable) {
        return gameInstanceRepository.findByCondition(condition, pageable).map(gameInstanceMapper::toResponseDto);
    }

    @Transactional
    public GameInstanceResponseDto update(Long id, GameInstanceRequestDto dto) {
        GameInstance instance = getById(id);
        if (!instance.getInventoryNumber().equals(dto.getInventoryNumber())
                && gameInstanceRepository.existsByInventoryNumber(dto.getInventoryNumber())) {
            throw new IllegalArgumentException("Inventory number already exists: " + dto.getInventoryNumber());
        }
        if (dto.getGameId() != null) {
            Game game = gameRepository.findById(dto.getGameId())
                    .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + dto.getGameId()));
            instance.setGame(game);
        }
        gameInstanceMapper.updateEntityFromDto(dto, instance);
        return gameInstanceMapper.toResponseDto(gameInstanceRepository.save(instance));
    }

    @Transactional
    public void delete(Long id) {
        if (!gameInstanceRepository.existsById(id)) {
            throw new EntityNotFoundException("GameInstance not found with id: " + id);
        }
        gameInstanceRepository.deleteById(id);
    }

    private GameInstance getById(Long id) {
        return gameInstanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GameInstance not found with id: " + id));
    }
}