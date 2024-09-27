package com.example.blogapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.blogapp.entity.Post;
import com.example.blogapp.entity.PostWithUser;

/**
 * 投稿：リポジトリ
 */
@Mapper
public interface PostMapper {
	/**
	 * 全ての投稿を取得
	 */
	List<PostWithUser> selectAll();

	/**
	 * 指定されたIDの投稿を取得
	 */
	PostWithUser selectById(@Param("id") Integer id);

	/**
	 * 投稿を登録
	 */
	void insert(Post post);

	/**
	 * 投稿を編集
	 */
	void update(Post post);

	/**
	 * 指定されたIDの投稿を削除
	 */
	void delete(@Param("id") Integer id);
}
