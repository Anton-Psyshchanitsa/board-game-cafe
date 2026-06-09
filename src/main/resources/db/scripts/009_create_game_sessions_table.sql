CREATE TABLE game_sessions (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT,
    game_id BIGINT NOT NULL,
    game_name VARCHAR(200),
    user_id BIGINT NOT NULL,
    session_date DATE NOT NULL,
    duration_minutes INT,
    rating INT,
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_game_sessions_booking
        FOREIGN KEY (booking_id)
        REFERENCES bookings(id)
        ON DELETE SET NULL,
    
    CONSTRAINT fk_game_sessions_game
        FOREIGN KEY (game_id)
        REFERENCES games(id)
        ON DELETE RESTRICT,
    
    CONSTRAINT fk_game_sessions_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE RESTRICT,
    
    CONSTRAINT chk_game_sessions_duration
        CHECK (duration_minutes IS NULL OR duration_minutes > 0),
    
    CONSTRAINT chk_game_sessions_rating
        CHECK (rating IS NULL OR (rating >= 1 AND rating <= 5))
);

CREATE INDEX idx_game_sessions_game_id ON game_sessions(game_id);
CREATE INDEX idx_game_sessions_user_id ON game_sessions(user_id);
CREATE INDEX idx_game_sessions_booking_id ON game_sessions(booking_id);
CREATE INDEX idx_game_sessions_session_date ON game_sessions(session_date);
CREATE INDEX idx_game_sessions_user_game ON game_sessions(user_id, game_id);
CREATE INDEX idx_game_sessions_rating ON game_sessions(rating);