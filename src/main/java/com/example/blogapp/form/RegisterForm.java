package com.example.blogapp.form;

import lombok.Data;

@Data
public class RegisterForm {
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
