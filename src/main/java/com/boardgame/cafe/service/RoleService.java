package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.RoleRequestDto;
import com.boardgame.cafe.dto.response.RoleResponseDto;
import com.boardgame.cafe.entity.Role;
import com.boardgame.cafe.mapper.RoleMapper;
import com.boardgame.cafe.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Transactional
    public RoleResponseDto create(RoleRequestDto dto) {
        if (roleRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Role with name " + dto.getName() + " already exists");
        }
        Role role = roleMapper.toEntity(dto);
        return roleMapper.toResponseDto(roleRepository.save(role));
    }

    @Transactional(readOnly = true)
    public RoleResponseDto findById(Long id) {
        return roleMapper.toResponseDto(getById(id));
    }

    @Transactional(readOnly = true)
    public RoleResponseDto findByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with name: " + name));
    }

    @Transactional(readOnly = true)
    public Page<RoleResponseDto> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::toResponseDto);
    }

    @Transactional
    public RoleResponseDto update(Long id, RoleRequestDto dto) {
        Role role = getById(id);
        roleMapper.updateEntityFromDto(dto, role);
        return roleMapper.toResponseDto(roleRepository.save(role));
    }

    @Transactional
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    private Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
    }
}