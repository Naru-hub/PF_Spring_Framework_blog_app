package com.example.blogapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投稿：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	/** id */
	private Integer id;
	/** 投稿を作成したユーザーのID */
	private Long userId;
	/** タイトル */
	private String title;
	/** 詳細 */
	private String description;
	/** 画像のパス */
	private String imagePath;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
