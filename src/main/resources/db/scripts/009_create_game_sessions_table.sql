CREATE TABLE game_sessions (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT,
    game_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    session_date DATE NOT NULL,
    duration_minutes INT,
    rating INT,
    review TEXT,
    is_edited BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_game_sessions_game_id ON game_sessions(game_id);
CREATE INDEX idx_game_sessions_user_id ON game_sessions(user_id);
CREATE INDEX idx_game_sessions_booking_id ON game_sessions(booking_id);
CREATE INDEX idx_game_sessions_session_date ON game_sessions(session_date);
CREATE INDEX idx_game_sessions_user_game ON game_sessions(user_id, game_id);
CREATE INDEX idx_game_sessions_rating ON game_sessions(rating);