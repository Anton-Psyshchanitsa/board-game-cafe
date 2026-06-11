CREATE TABLE user_profiles (
    user_id BIGSERIAL PRIMARY KEY,
    bio TEXT,
    avatar_url VARCHAR(500),
    date_of_birth DATE
);