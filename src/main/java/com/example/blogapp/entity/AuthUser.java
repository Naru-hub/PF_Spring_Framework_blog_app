package com.example.blogapp.entity;

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
	/** アカウントの有効/無効 */
    private boolean enabled;
}
