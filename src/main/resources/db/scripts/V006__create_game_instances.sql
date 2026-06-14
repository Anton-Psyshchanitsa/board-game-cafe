CREATE TABLE game_instances (
    id BIGSERIAL PRIMARY KEY,
    game_id BIGINT NOT NULL,
    inventory_number VARCHAR(50) NOT NULL UNIQUE,
    condition VARCHAR(20) NOT NULL DEFAULT 'good',
    purchase_date DATE,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT chk_game_instances_condition CHECK (condition IN ('excellent', 'good', 'fair', 'poor', 'damaged'))
);

ALTER TABLE game_instances ADD CONSTRAINT fk_game_instances_game FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE RESTRICT;

CREATE INDEX idx_game_instances_game_id ON game_instances(game_id);