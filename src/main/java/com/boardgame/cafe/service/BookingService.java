package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.BookingRequestDto;
import com.boardgame.cafe.dto.response.BookingResponseDto;
import com.boardgame.cafe.entity.*;
import com.boardgame.cafe.enums.BookingStatus;
import com.boardgame.cafe.mapper.BookingMapper;
import com.boardgame.cafe.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CafeTableRepository cafeTableRepository;
    private final GameInstanceRepository gameInstanceRepository;
    private final BookingGameRepository bookingGameRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingResponseDto create(BookingRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + dto.getUserId()));

        CafeTable table = cafeTableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new EntityNotFoundException("Table not found with id: " + dto.getTableId()));

        List<Booking> conflicts = bookingRepository.findConflictingBookings(
                dto.getTableId(), dto.getBookingDate(), dto.getStartTime(), dto.getEndTime());
        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException("Table is already booked for this time slot");
        }

        Booking booking = bookingMapper.toEntity(dto);
        booking.setUser(user);
        booking.setTable((Table) table);
        booking.setStatus(BookingStatus.PENDING);
        Booking savedBooking = bookingRepository.save(booking);

        if (dto.getGameInstanceIds() != null && !dto.getGameInstanceIds().isEmpty()) {
            for (Long instanceId : dto.getGameInstanceIds()) {
                GameInstance instance = gameInstanceRepository.findById(instanceId)
                        .orElseThrow(() -> new EntityNotFoundException("GameInstance not found with id: " + instanceId));
                if (!instance.getIsAvailable()) {
                    throw new IllegalArgumentException("GameInstance is not available: " + instanceId);
                }
                BookingGame bookingGame = new BookingGame();
                bookingGame.setBooking(savedBooking);
                bookingGame.setGameInstance(instance);
                bookingGameRepository.save(bookingGame);
                instance.setIsAvailable(false);
                gameInstanceRepository.save(instance);
            }
        }

        return bookingMapper.toResponseDto(bookingRepository.findById(savedBooking.getId()).orElseThrow());
    }

    @Transactional(readOnly = true)
    public BookingResponseDto findById(Long id) {
        return bookingMapper.toResponseDto(getById(id));
    }

    @Transactional(readOnly = true)
    public Page<BookingResponseDto> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable).map(bookingMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<BookingResponseDto> findByUserId(Long userId, Pageable pageable) {
        return bookingRepository.findByUserId(userId, pageable).map(bookingMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<BookingResponseDto> findByStatus(BookingStatus status, Pageable pageable) {
        return bookingRepository.findByStatus(status, pageable).map(bookingMapper::toResponseDto);
    }

    @Transactional
    public BookingResponseDto update(Long id, BookingRequestDto dto) {
        Booking booking = getById(id);
        if (dto.getTableId() != null) {
            CafeTable table = cafeTableRepository.findById(dto.getTableId())
                    .orElseThrow(() -> new EntityNotFoundException("Table not found with id: " + dto.getTableId()));
            booking.setTable((Table) table);
        }
        bookingMapper.updateEntityFromDto(dto, booking);
        return bookingMapper.toResponseDto(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponseDto cancel(Long id) {
        Booking booking = getById(id);
        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new IllegalArgumentException("Cannot cancel completed booking");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        List<BookingGame> bookingGames = bookingGameRepository.findByBookingId(id);
        for (BookingGame bookingGame : bookingGames) {
            GameInstance instance = bookingGame.getGameInstance();
            instance.setIsAvailable(true);
            gameInstanceRepository.save(instance);
        }
        return bookingMapper.toResponseDto(bookingRepository.save(booking));
    }

    @Transactional
    public void delete(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new EntityNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }

    private Booking getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
    }
}