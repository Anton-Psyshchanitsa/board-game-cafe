CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    user_name VARCHAR(100),
    user_phone VARCHAR(20),
    table_id BIGINT,
    table_number VARCHAR(10),
    booking_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    number_of_players INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    total_price DECIMAL(10, 2),
    special_requests TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_bookings_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE RESTRICT,
    
    CONSTRAINT fk_bookings_table
        FOREIGN KEY (table_id)
        REFERENCES tables(id)
        ON DELETE SET NULL,
    
    CONSTRAINT chk_bookings_time
        CHECK (end_time > start_time),
    
    CONSTRAINT chk_bookings_players
        CHECK (number_of_players > 0),
    
    CONSTRAINT chk_bookings_status
        CHECK (status IN ('pending', 'confirmed', 'completed', 'cancelled')),
    
    CONSTRAINT chk_bookings_price
        CHECK (total_price IS NULL OR total_price >= 0)
);

CREATE INDEX idx_bookings_user_id ON bookings(user_id);
CREATE INDEX idx_bookings_table_id ON bookings(table_id);
CREATE INDEX idx_bookings_booking_date ON bookings(booking_date);
CREATE INDEX idx_bookings_date_time ON bookings(booking_date, start_time);
CREATE INDEX idx_bookings_status ON bookings(status);