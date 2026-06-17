package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.GameSessionRequestDto;
import com.boardgame.cafe.dto.response.GameSessionResponseDto;
import com.boardgame.cafe.entity.Booking;
import com.boardgame.cafe.entity.Game;
import com.boardgame.cafe.entity.GameSession;
import com.boardgame.cafe.entity.User;
import com.boardgame.cafe.mapper.GameSessionMapper;
import com.boardgame.cafe.repository.BookingRepository;
import com.boardgame.cafe.repository.GameRepository;
import com.boardgame.cafe.repository.GameSessionRepository;
import com.boardgame.cafe.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameSessionService {

    private final GameSessionRepository gameSessionRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final GameSessionMapper gameSessionMapper;

    @Transactional
    public GameSessionResponseDto create(GameSessionRequestDto dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + dto.getGameId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + dto.getUserId()));

        GameSession session = gameSessionMapper.toEntity(dto);
        session.setGame(game);
        session.setUser(user);
        session.setIsEdited(false);

        if (dto.getBookingId() != null) {
            Booking booking = bookingRepository.findById(dto.getBookingId())
                    .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + dto.getBookingId()));
            session.setBooking(booking);
        }

        return gameSessionMapper.toResponseDto(gameSessionRepository.save(session));
    }

    @Transactional(readOnly = true)
    public GameSessionResponseDto findById(Long id) {
        return gameSessionMapper.toResponseDto(getById(id));
    }

    @Transactional(readOnly = true)
    public Page<GameSessionResponseDto> findAll(Pageable pageable) {
        return gameSessionRepository.findAll(pageable).map(gameSessionMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameSessionResponseDto> findByUserId(Long userId, Pageable pageable) {
        return gameSessionRepository.findByUserId(userId, pageable).map(gameSessionMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<GameSessionResponseDto> findByGameId(Long gameId, Pageable pageable) {
        return gameSessionRepository.findByGameId(gameId, pageable).map(gameSessionMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Double getAverageRating(Long gameId) {
        return gameSessionRepository.findAverageRatingByGameId(gameId);
    }

    @Transactional
    public GameSessionResponseDto update(Long id, GameSessionRequestDto dto) {
        GameSession session = getById(id);
        gameSessionMapper.updateEntityFromDto(dto, session);
        session.setIsEdited(true);
        return gameSessionMapper.toResponseDto(gameSessionRepository.save(session));
    }

    @Transactional
    public void delete(Long id) {
        if (!gameSessionRepository.existsById(id)) {
            throw new EntityNotFoundException("GameSession not found with id: " + id);
        }
        gameSessionRepository.deleteById(id);
    }

    private GameSession getById(Long id) {
        return gameSessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GameSession not found with id: " + id));
    }
}