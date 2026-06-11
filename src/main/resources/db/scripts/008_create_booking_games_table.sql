CREATE TABLE booking_games (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    game_instance_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_booking_games_booking_id ON booking_games(booking_id);
CREATE INDEX idx_booking_games_instance_id ON booking_games(game_instance_id);
CREATE UNIQUE INDEX idx_booking_games_unique ON booking_games(booking_id, game_instance_id);