package com.boardgame.cafe.repository;

import com.boardgame.cafe.entity.Game;
import com.boardgame.cafe.entity.GameInstance;
import com.boardgame.cafe.enums.GameCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameInstanceRepository extends JpaRepository<GameInstance, Long> {

    Page<GameInstance> findByGameId(Long gameId, Pageable pageable);

    Page<GameInstance> findByIsAvailableTrue(Pageable pageable);

    Page<GameInstance> findByCondition(GameCondition condition, Pageable pageable);

    Optional<GameInstance> findByInventoryNumber(String inventoryNumber);

    boolean existsByInventoryNumber(String inventoryNumber);

    Page<GameInstance> findAll(Pageable pageable);
}