package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.CafeTableRequestDto;
import com.boardgame.cafe.dto.response.CafeTableResponseDto;
import com.boardgame.cafe.entity.CafeTable;
import com.boardgame.cafe.mapper.CafeTableMapper;
import com.boardgame.cafe.repository.CafeTableRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CafeTableService {

    private final CafeTableRepository cafeTableRepository;
    private final CafeTableMapper cafeTableMapper;

    @Transactional
    public CafeTableResponseDto create(CafeTableRequestDto dto) {
        if (cafeTableRepository.existsByTableNumber(dto.getTableNumber())) {
            throw new IllegalArgumentException("Table number already exists: " + dto.getTableNumber());
        }
        CafeTable cafeTable = cafeTableMapper.toEntity(dto);
        return cafeTableMapper.toResponseDto(cafeTableRepository.save(cafeTable));
    }

    @Transactional(readOnly = true)
    public CafeTableResponseDto findById(Long id) {
        return cafeTableMapper.toResponseDto(getById(id));
    }

    @Transactional(readOnly = true)
    public Page<CafeTableResponseDto> findAll(Pageable pageable) {
        return cafeTableRepository.findAll(pageable).map(cafeTableMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<CafeTableResponseDto> findActive(Pageable pageable) {
        return cafeTableRepository.findByIsActiveTrue(pageable).map(cafeTableMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<CafeTableResponseDto> findByCapacity(Integer capacity, Pageable pageable) {
        return cafeTableRepository.findByCapacityGreaterThanEqual(capacity, pageable).map(cafeTableMapper::toResponseDto);
    }

    @Transactional
    public CafeTableResponseDto update(Long id, CafeTableRequestDto dto) {
        CafeTable cafeTable = getById(id);
        if (!cafeTable.getTableNumber().equals(dto.getTableNumber())
                && cafeTableRepository.existsByTableNumber(dto.getTableNumber())) {
            throw new IllegalArgumentException("Table number already exists: " + dto.getTableNumber());
        }
        cafeTableMapper.updateEntityFromDto(dto, cafeTable);
        return cafeTableMapper.toResponseDto(cafeTableRepository.save(cafeTable));
    }

    @Transactional
    public void delete(Long id) {
        if (!cafeTableRepository.existsById(id)) {
            throw new EntityNotFoundException("Table not found with id: " + id);
        }
        cafeTableRepository.deleteById(id);
    }

    private CafeTable getById(Long id) {
        return cafeTableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table not found with id: " + id));
    }
}