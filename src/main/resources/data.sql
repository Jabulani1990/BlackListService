--  INSERT INTO role (name) VALUES('ROLE_USER_ADMIN');
-- INSERT INTO users (email, role_fk) VALUES('nwajeigoddowell@gmail.com', 1);-- Check if the role exists
-- Insert or update the role
INSERT INTO role (name)
VALUES ('ROLE_USER_ADMIN') ON CONFLICT (name) DO NOTHING;
-- Insert or update the user
INSERT INTO users (email, role_fk)
VALUES ('nwajeigoddowell@gmail.com', 1) ON CONFLICT (email) DO NOTHING;