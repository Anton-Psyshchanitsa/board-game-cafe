package com.boardgame.cafe.service;

import com.boardgame.cafe.dto.request.LoginRequestDto;
import com.boardgame.cafe.dto.request.UserRequestDto;
import com.boardgame.cafe.dto.response.AuthResponseDto;
import com.boardgame.cafe.dto.response.UserResponseDto;
import com.boardgame.cafe.entity.RefreshToken;
import com.boardgame.cafe.entity.User;
import com.boardgame.cafe.repository.RefreshTokenRepository;
import com.boardgame.cafe.repository.UserRepository;
import com.boardgame.cafe.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Value("${security.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Transactional
    public AuthResponseDto login(LoginRequestDto dto, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        String accessToken = jwtService.generateAccessToken(userDetails);

        refreshTokenRepository.revokeAllByUserId(user.getId());
        String refreshTokenValue = createAndSaveRefreshToken(user);
        setRefreshTokenCookie(response, refreshTokenValue);

        return new AuthResponseDto(
                accessToken,
                "Bearer",
                user.getUsername(),
                user.getRole().getName()
        );
    }

    @Transactional
    public AuthResponseDto refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshTokenValue = extractRefreshTokenFromCookie(request);

        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));

        if (refreshToken.getIsRevoked()) {
            throw new IllegalArgumentException("Refresh token is revoked");
        }

        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Refresh token is expired");
        }

        User user = refreshToken.getUser();
        refreshTokenRepository.delete(refreshToken);

        String newRefreshTokenValue = createAndSaveRefreshToken(user);
        setRefreshTokenCookie(response, newRefreshTokenValue);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String accessToken = jwtService.generateAccessToken(userDetails);

        return new AuthResponseDto(
                accessToken,
                "Bearer",
                user.getUsername(),
                user.getRole().getName()
        );
    }

    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshTokenValue = extractRefreshTokenFromCookie(request);

        refreshTokenRepository.findByToken(refreshTokenValue)
                .ifPresent(rt -> {
                    rt.setIsRevoked(true);
                    refreshTokenRepository.save(rt);
                });

        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Transactional
    public UserResponseDto register(UserRequestDto dto) {
        return userService.create(dto);
    }

    private String createAndSaveRefreshToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(tokenValue);
        refreshToken.setUser(user);
        refreshToken.setCreatedAt(LocalDateTime.now());
        refreshToken.setExpiresAt(LocalDateTime.now().plusSeconds(refreshTokenExpiration / 1000));
        refreshToken.setIsRevoked(false);
        refreshTokenRepository.save(refreshToken);
        return tokenValue;
    }

    private void setRefreshTokenCookie(HttpServletResponse response, String refreshTokenValue) {
        Cookie cookie = new Cookie("refresh_token", refreshTokenValue);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge((int) (refreshTokenExpiration / 1000));
        response.addCookie(cookie);
    }

    private String extractRefreshTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            throw new IllegalArgumentException("No cookies found");
        }
        return Arrays.stream(request.getCookies())
                .filter(c -> "refresh_token".equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token cookie not found"));
    }
}