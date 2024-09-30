-- テーブルが存在したら削除する
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS auth_users;
-- テーブルの作成
-- ユーザー情報を格納するテーブル
CREATE TABLE auth_users
(
   -- id:主キー
   id serial PRIMARY KEY,
   -- ユーザー名：主キー
   username VARCHAR (50) NOT NULL UNIQUE,
   -- パスワード
   password VARCHAR (255) NOT NULL,
   -- メールアドレス
   email VARCHAR(100) NOT NULL UNIQUE,
   -- ユーザー画像パス
   user_image_path TEXT,
   -- アカウントの有効/無効
   enabled BOOLEAN DEFAULT TRUE,
   -- 作成日
   created_at TIMESTAMP without time zone,
   -- 更新日
   updated_at TIMESTAMP without time zone
);
-- 投稿(POST)テーブル
CREATE TABLE posts
(
   -- id:主キー
   id serial PRIMARY KEY,
   -- postを投稿したユーザーID
   user_id INT NOT NULL,
   -- タイトル:NULL不許可
   title varchar (255) NOT NULL,
   -- 詳細
   description TEXT,
   -- 画像パス
   image_path TEXT,
   -- 作成日
   created_at TIMESTAMP without time zone,
   -- 更新日
   updated_at TIMESTAMP without time zone,
   -- auth_usersテーブルへの外部キー制約
   FOREIGN KEY (user_id) REFERENCES auth_users(id)
);