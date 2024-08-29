-- テーブルが存在したら削除する
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS auth_users;
-- テーブルの作成
CREATE TABLE posts
(
   -- id:主キー
   id serial PRIMARY KEY,
   -- タイトル:NULL不許可
   title varchar (255) NOT NULL,
   -- 詳細
   description text,
   -- 画像パス
   image_path text,
   -- 作成日
   created_at timestamp without time zone,
   -- 更新日
   updated_at timestamp without time zone
);
-- user情報を格納するテーブル
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
   -- アカウントの有効/無効
   enabled BOOLEAN DEFAULT TRUE
);