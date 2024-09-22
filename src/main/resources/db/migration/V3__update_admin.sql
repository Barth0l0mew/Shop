UPDATE users
SET ( archive, email, password, role, username, basket_id) =
( FALSE, 'admin@example.com', '$2a$08$lP0KvTw9J8/9N37UBBwVOugEXIpQzFbBYe0A5Ne.T4wNOJrtopc9y', 'ADMIN', 'admin', NULL) where ID=1;

