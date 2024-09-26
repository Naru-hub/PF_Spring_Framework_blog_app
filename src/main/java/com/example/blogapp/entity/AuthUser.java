package com.example.blogapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
	/** id */
	private Long id;
	/** ユーザー名 */
	private String username;
	/** パスワード */
	private String password;
	/** メールアドレス */
	private String email;
	/** 画像のパス */
	private String imagePath;
	/** アカウントの有効/無効 */
    private boolean enabled;
    /** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
