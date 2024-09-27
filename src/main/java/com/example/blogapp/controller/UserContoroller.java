package com.example.blogapp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.blogapp.entity.AuthUser;
import com.example.blogapp.form.UserForm;
import com.example.blogapp.helper.UserHelper;
import com.example.blogapp.repository.AuthUserMapper;
import com.example.blogapp.service.ImageService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * User:コントローラ
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserContoroller {

	// 画像ファイル格納ディレクトリパス
	private final String IMAGE_UPLOAD_DIR_PATH = "/uploads/user/";

	/** DI */
	private final AuthUserMapper authUserMapper;
	private final UserHelper userHelper;
	private final ImageService imageService;

	/**
	 * User情報を表示
	 * @param model
	 * @return String ビュー名
	 */
	@GetMapping("")
	public String list(Model model, HttpSession session) {

		// 現在ログインしているユーザーのIDを取得
		Long currentUserId = userHelper.getCurrentUserId();
		// ログを追加
		System.out.println("Current User ID: " + currentUserId);
		
		// 認証情報からユーザーのIDが取得できなかった場合
	    if(currentUserId == null) {
	    	// セッションからユーザーIDを取得
	        currentUserId = (Long) session.getAttribute("currentUserId");
	        System.out.println("Session Current User ID: " + currentUserId);

	    }
		
		// 現在ログインしているユーザーのIDでユーザー情報を取得
		AuthUser userInfo = authUserMapper.selectByLoginUserId(currentUserId);
		  // ログを追加
	    System.out.println("User Info: " + userInfo);
		
		model.addAttribute("userInfo", userInfo);
		return "user/info";
	}

	/**
	 * User情報編集画面を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String ビュー名
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes attributes, HttpSession session) {
		// 現在ログインしているユーザーのIDを取得
		Long currentUserId = userHelper.getCurrentUserId();
		System.out.println("Edit Current User ID: " + currentUserId);
		
		
		// 認証情報からユーザーのIDが取得できなかった場合
		if(currentUserId == null) {
			// セッションからユーザーIDを取得
	        currentUserId = (Long) session.getAttribute("currentUserId");
	        System.out.println("Edit Session Current User ID: " + currentUserId);
		}
	        
		// 現在ログインしているユーザーのIDでユーザー情報を取得
		AuthUser target = authUserMapper.selectByLoginUserId(currentUserId);

		if (target != null) {
			// User情報が存在する場合、ログインしているユーザーと同じであるか確認
			if (target.getId().equals(currentUserId)) {
				// ユーザーが現在ログインしているユーザーであれば、Formに変換、編集画面に遷移
				UserForm form = UserHelper.convertUserForm(target);
				// モデルに格納
				model.addAttribute("userForm", form);
				return "user/form";
			} else {
				// Userが異なる場合、エラーメッセージを表示してリダイレクト
				attributes.addFlashAttribute("errorMessage", "編集権限がありません");
				return "redirect:/user";
			}

		} else {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// User情報画面へリダイレクト
			return "redirect:/user";

		}
	}

	/**
	 * Userの情報を更新
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @return String ビュー名
	 */
	@PostMapping("/update")
	public String update(@Validated UserForm form,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes attributes,
			HttpSession session) {
		// バリデーションチェック
		// 入力チェックNG:入力画面を表示する
		if (bindingResult.hasErrors()) {
			return "user/form";
		}

		// 現在のログインユーザーIDを取得
		Long currentUserId = userHelper.getCurrentUserId();
		System.out.println("Update Current User ID: " + currentUserId);
		
		// 認証情報からユーザーのIDが取得できなかった場合
	    if(currentUserId == null) {
	    	// セッションからユーザーIDを取得
	        currentUserId = (Long) session.getAttribute("currentUserId");
	        System.out.println("Update Session Current User ID: " + currentUserId);

	    }
		// 元のユーザー情報を取得
		AuthUser existingUserInfo = authUserMapper.selectByLoginUserId(form.getId());

		// 投稿が存在しない、またはユーザーIDが一致しない場合
		if (existingUserInfo == null || !existingUserInfo.getId().equals(currentUserId)) {
			attributes.addFlashAttribute("errorMessage", "User情報が見つからないか、編集権限がありません");
			return "redirect:/user";
		}

		// 編集するイメージファイル名とイメージパス
		String newImageFilename = null;
		String newImagePath = null;

		try {
			// 新しい画像ファイルの処理
			if (form.getFile() != null && !form.getFile().isEmpty()) {
				try {
					// 新しい画像のファイル名を取得
					newImageFilename = imageService.handleImageUpload(form.getFile(), "user");
					// ファイルパスを相対パスでセットする
					newImagePath = this.IMAGE_UPLOAD_DIR_PATH + newImageFilename;
					form.setImagePath(newImagePath);

				} catch (IOException e) {
					// 画像のアップロードに失敗した場合の処理
					bindingResult.reject("fileUploadError", "画像のアップロードに失敗しました。");
					return "user/form";
				}
			} else {
				// 画像ファイルが選択されていない場合は既存の画像パスをそのまま使用
				if (existingUserInfo != null && existingUserInfo.getImagePath() != null) {
					form.setImagePath(existingUserInfo.getImagePath());
				}
			}

			// エンティティへの変換
			AuthUser authUser = UserHelper.convertAuthUser(form);
			// 更新処理
			authUserMapper.updateUser(authUser);

			// 古い画像ファイルの削除（更新処理が成功した場合のみ）
			if (existingUserInfo != null && existingUserInfo.getImagePath() != null) {
				// 相対パスからファイル名を取得
				String oldImageFilename = existingUserInfo.getImagePath().replace(this.IMAGE_UPLOAD_DIR_PATH, "");
				if (newImageFilename != null && !oldImageFilename.equals(newImageFilename)) {
					imageService.deleteImage(oldImageFilename, "user");
				}
			}

			// 画像の保存処理が終わるまで待機
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			}

			// ユーザー情報が更新された後、セッションにユーザーIDを保存
		    session.setAttribute("currentUserId", currentUserId);
			// フラッシュメッセージ
			attributes.addFlashAttribute("message", "ユーザー情報が更新されました");
			// RPGパターン
			return "redirect:/user";

		} catch (Exception e) {
			// ユーザー情報の更新処理でエラーが発生した場合の処理
			if (newImageFilename != null) {
				// 新しい画像ファイルを削除
				imageService.deleteImage(newImageFilename, "user");
			}

			bindingResult.reject("updateError", "ユーザー情報の更新に失敗しました。");
			return "user/form";
		}
	}

}
