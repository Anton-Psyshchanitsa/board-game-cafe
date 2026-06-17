package com.boardgame.cafe.repository;

import com.boardgame.cafe.entity.Booking;
import com.boardgame.cafe.enums.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Page<Booking> findByUserId(Long userId, Pageable pageable);

    Page<Booking> findByStatus(BookingStatus status, Pageable pageable);

    Page<Booking> findByBookingDate(LocalDate bookingDate, Pageable pageable);

    Page<Booking> findByTableId(Long tableId, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.table.id = :tableId " +
            "AND b.bookingDate = :date " +
            "AND b.status NOT IN ('CANCELLED') " +
            "AND (b.startTime < :endTime AND b.endTime > :startTime)")
    List<Booking> findConflictingBookings(
            @Param("tableId") Long tableId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    Page<Booking> findAll(Pageable pageable);
}