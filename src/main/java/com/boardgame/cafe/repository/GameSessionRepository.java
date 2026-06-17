package com.boardgame.cafe.repository;

import com.boardgame.cafe.entity.GameSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    Page<GameSession> findByUserId(Long userId, Pageable pageable);

    Page<GameSession> findByGameId(Long gameId, Pageable pageable);

    List<GameSession> findByBookingId(Long bookingId);

    Page<GameSession> findBySessionDate(LocalDate sessionDate, Pageable pageable);

    @Query("SELECT AVG(gs.rating) FROM GameSession gs WHERE gs.game.id = :gameId")
    Double findAverageRatingByGameId(@Param("gameId") Long gameId);

    Page<GameSession> findAll(Pageable pageable);
}