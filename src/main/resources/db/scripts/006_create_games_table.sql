CREATE TABLE game_instances (
    id BIGSERIAL PRIMARY KEY,
    game_id BIGINT NOT NULL,
    inventory_number VARCHAR(50) NOT NULL UNIQUE,
    condition VARCHAR(20) NOT NULL DEFAULT 'good',
    purchase_date DATE,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_game_instances_game_id ON game_instances(game_id);
CREATE INDEX idx_game_instances_inventory_number ON game_instances(inventory_number);
CREATE INDEX idx_game_instances_is_available ON game_instances(is_available);
CREATE INDEX idx_game_instances_condition ON game_instances(condition);