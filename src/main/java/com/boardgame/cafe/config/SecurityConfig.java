package com.boardgame.cafe.config;

import com.boardgame.cafe.security.JwtAuthenticationFilter;
import com.boardgame.cafe.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/games/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/game-categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tables/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/game-instances/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/games/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/games/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/games/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/game-categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/game-categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/game-categories/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/tables/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/tables/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tables/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/game-instances/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/game-instances/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/game-instances/**").hasRole("ADMIN")

                        .requestMatchers("/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/role/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/bookings").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/bookings/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}