package com.example.blogapp.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.blogapp.entity.AuthUser;

@Mapper
public interface AuthUserMapper {
	/**
	 * ユーザー名でログイン情報を取得します。
	 */
	 AuthUser selectByUsername(String username);
	 void insertUser(AuthUser authuser);
}
