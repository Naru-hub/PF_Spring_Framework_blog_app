package com.example.blogapp.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.blogapp.entity.AuthUser;

@Mapper
public interface AuthUserMapper {
	/**
	 * ユーザー名でログイン情報を取得
	 */
	 AuthUser selectByUsername(String username);
	 
	 /**
	  * ユーザー新規登録
	  */
	 void insertUser(AuthUser authuser);
}
