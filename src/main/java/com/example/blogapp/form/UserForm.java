package com.example.blogapp.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	/** id */
	private Long id;
	
	/** ユーザー名 */
	@NotEmpty(message = "ユーザー名は必須です。")
	private String username;
	
	/** メールアドレス */
	@NotEmpty(message = "メールアドレスは必須です。")
	@Email(message = "有効なメールアドレスを入力してください。")
	private String email;
	
	/** 画像ファイル */
    private MultipartFile file;
    
    /** 画像のパス */
    private String imagePath; 
	
}
