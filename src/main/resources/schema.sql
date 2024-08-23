-- テーブルが存在したら削除する
DROP TABLE IF EXISTS posts;
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