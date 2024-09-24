package com.example.blogapp.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.blogapp.entity.AuthUser;
import com.example.blogapp.repository.AuthUserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserHelper {
	
	/** DI */
	private final AuthUserMapper authUserMapper;
	
	/**
	 * 現在認証されているユーザーのIDを取得するヘルパーメソッド
	 * @return ID || null
	 */
	public Long getCurrentUserId() {
	    // 現在の認証情報を取得
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    // 認証情報からユーザー名を取得
	    String username = authentication.getName();

	    // ユーザー名からユーザーオブジェクトを取得
	    AuthUser user = authUserMapper.selectByUserId(username);

	    // ユーザーが見つかればそのIDを返す、見つからなければnullを返す
	    return user != null ? user.getId() : null;
	}
}
