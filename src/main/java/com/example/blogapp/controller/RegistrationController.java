package com.example.blogapp.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public String registerUser(@Validated @ModelAttribute RegisterForm form, BindingResult bindingResult, Model model) {
		// === バリデーションチェック ===
		// ユーザー登録時の入力チェック
		if (bindingResult.hasErrors()) {
			// バリデーションエラーがある場合入力画面を表示
			return "register";
		}

		try {
			AuthUser user = new AuthUser();
			user.setUsername(form.getUsername());
			user.setPassword(passwordEncoder.encode(form.getPassword()));
			user.setEmail(form.getEmail());
			user.setEnabled(true);
			userDetailsService.registerUser(user);
			return "redirect:/posts";
		} catch (DataIntegrityViolationException e) {
			// ユーザー登録の失敗時にエラーメッセージをモデルに追加
			model.addAttribute("registerForm", form);
			model.addAttribute("errorMessage", "ユーザー名またはメールアドレスがすでに使用されています。");
			return "register";
		}
	}
}
