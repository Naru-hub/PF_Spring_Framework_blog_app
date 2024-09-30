-- userのデータ
-- 1人目のデータ
INSERT INTO auth_users (username, password, email, created_at, updated_at)
VALUES ('テスト　太郎', '$2a$10$bnXFUQawIzzLGELILFMjkego0N50DKS1J/XVcHBJ2YO3I3Zh8yMhS', 'test@test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
UPDATE auth_users 
SET user_image_path = '/uploads/default_img/user1_default.jpg' where id = 1;

-- 2人目のデータ
INSERT INTO auth_users (username, password, email, created_at, updated_at)
VALUES ('テスト　花子', '$2a$10$bnXFUQawIzzLGELILFMjkego0N50DKS1J/XVcHBJ2YO3I3Zh8yMhS', 'taro@test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
UPDATE auth_users 
SET user_image_path = '/uploads/default_img/user2_default.jpg' where id = 2;

-- 投稿のデータ
-- 1件目のデータ
INSERT INTO posts (user_id, title, description, image_path, created_at, updated_at)
VALUES ('1', 'スッキリJava', '優しい解説で分かりやすい。オブジェクト指向入門に最適です！！', '/uploads/default_img/java_default.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- 2件目のデータ
INSERT INTO posts (user_id, title, description, image_path, created_at, updated_at)
VALUES ('2', 'ソフトウェアエンジニアリングの新人研修', 'webの大まかな流れがつかめ、開発がどのように進んでいくかを知るのによかった。　新人の時に読むことをおすすめします。', '/uploads/default_img/it_basic_default.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- 3件目のデータ
INSERT INTO posts (user_id, title, description, image_path, created_at, updated_at)
VALUES ('1', 'スッキリSQL', 'SQLが初めての方に特にお勧めです。入門として非常にわかりやすい構成です。', '/uploads/default_img/sql_default.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);