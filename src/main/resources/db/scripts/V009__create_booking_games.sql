CREATE TABLE booking_games (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    game_instance_id BIGINT,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT uq_booking_games_booking_game UNIQUE (booking_id, game_id)
);

ALTER TABLE booking_games ADD CONSTRAINT fk_booking_games_booking FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE;
ALTER TABLE booking_games ADD CONSTRAINT fk_booking_games_game FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE RESTRICT;
ALTER TABLE booking_games ADD CONSTRAINT fk_booking_games_instance FOREIGN KEY (game_instance_id) REFERENCES game_instances(id) ON DELETE SET NULL;

CREATE INDEX idx_booking_games_game_id ON booking_games(game_id);
CREATE INDEX idx_booking_games_instance_id ON booking_games(game_instance_id);