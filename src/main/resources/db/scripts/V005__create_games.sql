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
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT chk_games_players CHECK (min_players > 0 AND max_players >= min_players),
    CONSTRAINT chk_games_duration CHECK (avg_duration_minutes > 0),
    CONSTRAINT chk_games_difficulty CHECK (difficulty_level IN ('easy', 'medium', 'hard', 'expert')),
    CONSTRAINT chk_games_year CHECK (year_published > 1900 AND year_published <= EXTRACT(YEAR FROM CURRENT_DATE))
);

ALTER TABLE games ADD CONSTRAINT fk_games_category FOREIGN KEY (category_id) REFERENCES game_categories(id) ON DELETE RESTRICT;
ALTER TABLE games ADD CONSTRAINT fk_games_creator FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE SET NULL;
ALTER TABLE games ADD CONSTRAINT fk_games_updater FOREIGN KEY (updater_id) REFERENCES users(id) ON DELETE SET NULL;

CREATE INDEX idx_games_name ON games(name);
CREATE INDEX idx_games_category_id ON games(category_id);
CREATE INDEX idx_games_players ON games(min_players, max_players);