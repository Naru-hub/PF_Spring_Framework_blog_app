package com.example.blogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home:コントローラ
 */
@Controller
@RequestMapping("/")
public class HomeContoroller {

	/**
	 * Home画面を表示する
	 */
	@GetMapping
	public String showHome() {
		// Homeに遷移
		return "home";
	}
}
