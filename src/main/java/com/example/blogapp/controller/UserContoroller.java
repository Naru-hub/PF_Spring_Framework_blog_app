package com.example.blogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.blogapp.entity.AuthUser;
import com.example.blogapp.helper.UserHelper;
import com.example.blogapp.repository.AuthUserMapper;

import lombok.RequiredArgsConstructor;

/**
 * User:コントローラ
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserContoroller {
	
	/** DI */
	private final AuthUserMapper authUserMapper;
	private final UserHelper userHelper;
	
	/**
	 * User情報を表示
	 * @param model
	 * @return String ビュー名
	 */
	@GetMapping("")
	public String list(Model model) {
		Long currentUserId = userHelper.getCurrentUserId();
		AuthUser userInfo = authUserMapper.selectByLoginUserId(currentUserId);
		model.addAttribute("userInfo",  userInfo);
		return "user/info";
	}
}
