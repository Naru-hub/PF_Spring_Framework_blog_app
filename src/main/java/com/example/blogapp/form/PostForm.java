package com.example.blogapp.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投稿のForm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
	
	/** ID */
	private Integer id;
	
	/** タイトル */
	@NotBlank(message = "タイトルは必須項目です。")
	private String title;
	
	/** 詳細 */
	private String description;
	
	/** 画像ファイル */
    private MultipartFile file;
    
    /** 画像のパス */
    private String imagePath; 
    
	/** 新規判定 */
	private Boolean isNew;

}
