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
	  * ユーザー名でユーザーIDを取得
	  */
	 AuthUser selectByUserId(String username);
	 
	 /**
	  * ログインしているユーザーのIDでユーザー情報を検索
	  */
	 AuthUser selectByLoginUserId(Long id);
	 
	 /**
	  * ユーザー新規登録
	  */
	 void insertUser(AuthUser authuser);
}
