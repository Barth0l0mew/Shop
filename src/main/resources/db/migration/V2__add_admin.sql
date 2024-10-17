INSERT INTO users (id, archive, email, password, role, username, basket_id)
VALUES (1, FALSE, 'admin@example.com', 'pass', 'ADMIN', 'admin', NULL);

ALTER SEQUENCE user_seq RESTART with 2;