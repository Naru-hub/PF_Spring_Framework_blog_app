package com.example.blogapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 投稿：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//親クラスのフィールドを考慮
@EqualsAndHashCode(callSuper = true) 
public class PostWithUser extends Post {
	/** ユーザー名 */
    private String username;
    /** ユーザー画像のパス */
    private String userImagePath;
}
