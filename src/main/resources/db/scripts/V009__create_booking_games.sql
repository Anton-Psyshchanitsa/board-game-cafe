CREATE TABLE booking_games (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    game_instance_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE booking_games ADD CONSTRAINT fk_booking_games_booking FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE;
ALTER TABLE booking_games ADD CONSTRAINT fk_booking_games_instance FOREIGN KEY (game_instance_id) REFERENCES game_instances(id) ON DELETE RESTRICT;

CREATE INDEX idx_booking_games_instance_id ON booking_games(game_instance_id);