CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_roles_updated_at ON roles;
CREATE TRIGGER trg_roles_updated_at BEFORE UPDATE ON roles FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

DROP TRIGGER IF EXISTS trg_users_updated_at ON users;
CREATE TRIGGER trg_users_updated_at BEFORE UPDATE ON users FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

DROP TRIGGER IF EXISTS trg_games_updated_at ON games;
CREATE TRIGGER trg_games_updated_at BEFORE UPDATE ON games FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

DROP TRIGGER IF EXISTS trg_game_instances_updated_at ON game_instances;
CREATE TRIGGER trg_game_instances_updated_at BEFORE UPDATE ON game_instances FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

DROP TRIGGER IF EXISTS trg_tables_updated_at ON tables;
CREATE TRIGGER trg_tables_updated_at BEFORE UPDATE ON tables FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

DROP TRIGGER IF EXISTS trg_bookings_updated_at ON bookings;
CREATE TRIGGER trg_bookings_updated_at BEFORE UPDATE ON bookings FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

DROP TRIGGER IF EXISTS trg_game_sessions_updated_at ON game_sessions;
CREATE TRIGGER trg_game_sessions_updated_at BEFORE UPDATE ON game_sessions FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();