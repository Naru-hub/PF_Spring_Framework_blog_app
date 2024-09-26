-- userのデータ
INSERT INTO auth_users (username, password, email, created_at, updated_at)
VALUES ('testUser', '$2a$10$bnXFUQawIzzLGELILFMjkego0N50DKS1J/XVcHBJ2YO3I3Zh8yMhS', 'test@test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 1件目のデータ
INSERT INTO posts (user_id, title, description, image_path, created_at, updated_at)
VALUES ('1', 'スッキリJava', '優しい解説で分かりやすい', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- 2件目のデータ
INSERT INTO posts (user_id, title, description, image_path, created_at, updated_at)
VALUES ('1', 'スッキリSQL', '初心者でもわかりやすい構成', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- 3件目のデータ
INSERT INTO posts (user_id, title, description, image_path, created_at, updated_at)
VALUES ('1', 'プロジェクトマネジメント', 'webの大まかな流れがつかめる', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);