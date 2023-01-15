#admin/datnv007
#user/user
INSERT INTO users (username, password, user_role, created_by, created_date, last_modified_by, last_modified_date, deleted) 
VALUES 
    ('admin', '$2a$10$7Dn3U.RcOWKogabLkpMRQOes.0odiC6gXCcE6C4SF7qJIjLohW/Em', 'ROLE_ADMIN', 'system', '2023-01-15 11:44:27.964830', 'system', '2023-01-15 11:44:27.964830', 0),
    ('user', '$2a$10$91uJLc6pQaxuK4fG6Td5.ue9cE8dUE6CUiMWCoBgYvJr2sV1DVeUa', 'ROLE_USER', 'system', '2023-01-15 11:44:27.964830', 'system', '2023-01-15 11:44:27.964830', 0);