INSERT INTO role (name)
VALUES ('ROLE_USER_ADMIN') ON CONFLICT (name) DO NOTHING;
INSERT INTO users (email, role_fk)
VALUES ('reiymondjon134@gmail.com', 1) ON CONFLICT (email) DO NOTHING;