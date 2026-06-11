CREATE TABLE games (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    category_id BIGINT NOT NULL,
    min_players INT NOT NULL,
    max_players INT NOT NULL,
    avg_duration_minutes INT NOT NULL,
    difficulty_level VARCHAR(20) NOT NULL,
    year_published INT,
    publisher VARCHAR(100),
    image_url VARCHAR(500),
    creator_id BIGINT,
    updater_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_games_name ON games(name);
CREATE INDEX idx_games_category_id ON games(category_id);
CREATE INDEX idx_games_players ON games(min_players, max_players);
CREATE INDEX idx_games_difficulty ON games(difficulty_level);