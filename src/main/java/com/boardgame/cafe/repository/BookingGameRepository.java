package com.boardgame.cafe.repository;

import com.boardgame.cafe.entity.BookingGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingGameRepository extends JpaRepository<BookingGame, Long> {

    List<BookingGame> findByBookingId(Long bookingId);

    List<BookingGame> findByGameInstanceId(Long gameInstanceId);

    boolean existsByBookingIdAndGameInstanceId(Long bookingId, Long gameInstanceId);

    void deleteByBookingId(Long bookingId);
}