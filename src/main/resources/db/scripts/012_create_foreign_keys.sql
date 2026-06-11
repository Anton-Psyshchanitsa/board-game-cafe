ALTER TABLE users
ADD CONSTRAINT fk_users_role
FOREIGN KEY (role_id)
REFERENCES roles(id)
ON DELETE RESTRICT;

ALTER TABLE user_profiles
ADD CONSTRAINT fk_user_profiles_user
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE;

ALTER TABLE games
ADD CONSTRAINT fk_games_category
FOREIGN KEY (category_id)
REFERENCES game_categories(id)
ON DELETE RESTRICT;

ALTER TABLE games
ADD CONSTRAINT fk_games_creator
FOREIGN KEY (creator_id)
REFERENCES users(id)
ON DELETE SET NULL;

ALTER TABLE games
ADD CONSTRAINT fk_games_updater
FOREIGN KEY (updater_id)
REFERENCES users(id)
ON DELETE SET NULL;

ALTER TABLE game_instances
ADD CONSTRAINT fk_game_instances_game
FOREIGN KEY (game_id)
REFERENCES games(id)
ON DELETE RESTRICT;

ALTER TABLE bookings
ADD CONSTRAINT fk_bookings_user
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE RESTRICT;

ALTER TABLE bookings
ADD CONSTRAINT fk_bookings_table
FOREIGN KEY (table_id)
REFERENCES tables(id)
ON DELETE SET NULL;

ALTER TABLE bookings
ADD CONSTRAINT fk_bookings_creator
FOREIGN KEY (creator_id)
REFERENCES users(id)
ON DELETE SET NULL;

ALTER TABLE bookings
ADD CONSTRAINT fk_bookings_updater
FOREIGN KEY (updater_id)
REFERENCES users(id)
ON DELETE SET NULL;

ALTER TABLE booking_games
ADD CONSTRAINT fk_booking_games_booking
FOREIGN KEY (booking_id)
REFERENCES bookings(id)
ON DELETE CASCADE;

ALTER TABLE booking_games
ADD CONSTRAINT fk_booking_games_instance
FOREIGN KEY (game_instance_id)
REFERENCES game_instances(id)
ON DELETE RESTRICT;

ALTER TABLE game_sessions
ADD CONSTRAINT fk_game_sessions_booking
FOREIGN KEY (booking_id)
REFERENCES bookings(id)
ON DELETE SET NULL;

ALTER TABLE game_sessions
ADD CONSTRAINT fk_game_sessions_game
FOREIGN KEY (game_id)
REFERENCES games(id)
ON DELETE RESTRICT;

ALTER TABLE game_sessions
ADD CONSTRAINT fk_game_sessions_user
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE RESTRICT;