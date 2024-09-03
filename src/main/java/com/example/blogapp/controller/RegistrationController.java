package com.example.blogapp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blogapp.entity.AuthUser;
import com.example.blogapp.form.RegisterForm;
import com.example.blogapp.service.impl.LoginUserDatailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
	/** DI */
	private final LoginUserDatailsServiceImpl userDetailsService;
	private final PasswordEncoder passwordEncoder;

	/**
	 * ユーザーの登録フォームを表示
	 * @param model
	 * @return String ビュー名
	 */
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		 model.addAttribute("registerForm", new RegisterForm()); 
		return "register";
	}

	/**
	 * ユーザーの新規登録
	 * @param form
	 * @return String ビュー名
	 */
	@PostMapping("/register")
	public String registerUser(@ModelAttribute RegisterForm form) {
		AuthUser user = new AuthUser();
		user.setUsername(form.getUsername());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setEmail(form.getEmail());
		user.setEnabled(true);
		userDetailsService.registerUser(user);
		return "redirect:/login";
	}
}
