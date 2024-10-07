UPDATE users
SET ( archive, email, password, role, username, basket_id) =
( FALSE, 'admin@example.com', '$2a$08$9lqk0pZZWpEV0jb8DALopO7mElhJQe7QlhM11ltnaX3Ugo839zqMG', 'ADMIN', 'admin', NULL) where ID=1;

