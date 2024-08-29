package com.example.blogapp.service.impl;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blogapp.entity.AuthUser;
import com.example.blogapp.entity.LoginUser;
import com.example.blogapp.repository.AuthUserMapper;

import lombok.RequiredArgsConstructor;

/**
* カスタム認証サービス
*/
@Service
@RequiredArgsConstructor
public class LoginUserDatailsServiceImpl implements UserDetailsService {
	/** DI */
	private final AuthUserMapper authUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// 「認証テーブル」からデータを取得
		AuthUser authUser = authUserMapper.selectByUsername(username);

		// 対象データがあれば、UserDetailsの実装クラスを返す
		if (authUser != null) {
			// 対象データが存在する
			// UserDetailsの実装クラスを返す
			return new LoginUser(authUser.getUsername(),
					authUser.getPassword(),
					Collections.emptyList());
		} else {
			// 対象データが存在しない
			throw new UsernameNotFoundException(
					username + " => 指定しているユーザー名は存在しません");
		}
	}
	
	public void registerUser(AuthUser authUser) {
		authUserMapper.insertUser(authUser);
    }
}
