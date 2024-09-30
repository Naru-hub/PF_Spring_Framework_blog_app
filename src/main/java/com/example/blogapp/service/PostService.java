package com.example.blogapp.service;

import java.util.List;

import com.example.blogapp.entity.Post;
import com.example.blogapp.entity.PostWithUser;

/**
 * Post:サービス
 */
public interface PostService {
	/**
	 * 全ての投稿を検索
	 */
	List<PostWithUser> findAllPost();

	/**
	 * 指定されたIDの投稿を検索
	 */
	PostWithUser findByIdPost(Integer id);

	/**
	 * 投稿を新規登録 
	 */
	void insertPost(Post post);

	/**
	 * 投稿の更新
	 */
	void updatePost(Post post);

	/**
	 * 指定されたIDの投稿を削除
	 */
	void deletePost(Integer id);
}
