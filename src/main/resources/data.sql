-- Password: pass
INSERT INTO users (username, password, enabled)
values ('user',
'$2y$12$oZ2sqFrVwI7bOB5HRXAdkeOPNizHSQ.sm0QBYIovbY1TYPA0rbGQ2',
true);

-- Password: pass
INSERT INTO users (username, password, enabled)
values ('admin',
'$2y$12$oZ2sqFrVwI7bOB5HRXAdkeOPNizHSQ.sm0QBYIovbY1TYPA0rbGQ2',
true);

INSERT INTO authorities (username, authority)
values ('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN');