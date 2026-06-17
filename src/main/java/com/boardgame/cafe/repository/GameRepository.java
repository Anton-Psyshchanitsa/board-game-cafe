package com.boardgame.cafe.repository;

import com.boardgame.cafe.entity.Game;
import com.boardgame.cafe.enums.DifficultyLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Page<Game> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Game> findByDifficultyLevel(DifficultyLevel difficultyLevel, Pageable pageable);

    Page<Game> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT g FROM Game g WHERE g.minPlayers <= :players AND g.maxPlayers >= :players")
    Page<Game> findByPlayerCount(@Param("players") Integer players, Pageable pageable);

    Page<Game> findAll(Pageable pageable);
}