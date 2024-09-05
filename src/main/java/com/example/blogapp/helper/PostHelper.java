package com.example.blogapp.helper;

import com.example.blogapp.entity.Post;
import com.example.blogapp.form.PostForm;

/** Postヘルパー */
public class PostHelper {
	/**
	 * Postへの変換
	 * @param form
	 * @return post オブジェクト
	 */
	public static Post convertPost(PostForm form) {
		Post post = new Post();
		post.setId(form.getId());
		post.setUserId(form.getUserId());
		post.setTitle(form.getTitle());
		post.setDescription(form.getDescription());
		/** 画像のパスをセット */
		post.setImagePath(form.getImagePath());
		return post;
	}

	/**
	 * PostFormへの変換
	 * @param post
	 * @return form (postの値をセット)
	 */
	public static PostForm convertPostForm(Post post) {
		PostForm form = new PostForm();
		form.setId(post.getId());
		form.setUserId(post.getUserId());
		form.setTitle(post.getTitle());
		form.setDescription(post.getDescription());
		/** 画像のパスをセット */
		form.setImagePath(post.getImagePath());
		// 更新画面設定
		form.setIsNew(false);
		return form;
	}
}
