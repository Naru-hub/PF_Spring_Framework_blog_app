package com.example.blogapp.form;

import java.util.Objects;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterForm {
	/** id */
	private Long id;
	
	/** ユーザー名 */
	@NotEmpty(message = "ユーザー名は必須です。")
	private String username;
	
	/** パスワード */
	@NotEmpty(message = "パスワードは必須です。")
	private String password;
	
	/** 確認用パスワード */
	private String confirmPassword;
	
	/** メールアドレス */
	@NotEmpty(message = "メールアドレスは必須です。")
	@Email(message = "有効なメールアドレスを入力してください。")
	private String email;
	
	/** アカウントの有効/無効 */
	private boolean enabled;
	
	/** パスワードと確認用パスワードの一致チェック */
	@AssertTrue(message="パスワードと確認用パスワードが一致しません")
	public boolean isSamePassword() {
		return Objects.equals(password, confirmPassword);
	}
}
