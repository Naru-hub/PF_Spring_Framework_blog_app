package com.example.blogapp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.blogapp.entity.Post;
import com.example.blogapp.form.PostForm;
import com.example.blogapp.helper.PostHelper;
import com.example.blogapp.helper.UserHelper;
import com.example.blogapp.service.ImageService;
import com.example.blogapp.service.PostService;

import lombok.RequiredArgsConstructor;

/**
 * Post:コントローラ
 */
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

	/** DI */
	private final PostService postService;
	private final ImageService imageService;
	private final UserHelper userHelper;

	/**
	 * 投稿一覧を表示
	 * @param model
	 * @return String ビュー名
	 */
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("posts", postService.findAllPost());
		return "post/list";
	}

	/**
	 * 指定されたIDの投稿の詳細を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String ビュー名
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 投稿IDに対応する投稿を取得
		Post post = postService.findByIdPost(id);

		// 対象データがあるか
		if (post != null) {
			// データがある場合はモデルに格納
			model.addAttribute("post", postService.findByIdPost(id));

			// 現在ログインしているユーザーIDを取得してモデルに追加
			Long currentUserId = userHelper.getCurrentUserId();
			model.addAttribute("currentUserId", currentUserId);

			return "post/detail";
		} else {
			// 対象データがない場合
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			return "redirect:/posts";
		}
	}

	/**
	 * 新規登録画面を表示
	 * @param form
	 * @return String ビュー名
	 */
	@GetMapping("/form")
	public String newPost(@ModelAttribute PostForm form) {
		// 新規登録画面の設定
		form.setIsNew(true);
		return "post/form";
	}

	/**
	 * 新規登録を実行
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @return String ビュー名
	 */
	@PostMapping("/save")
	public String create(@Validated PostForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes) {
		// === バリデーションチェック ===
		// 入力チェックNG:入力画面を表示する
		if (bindingResult.hasErrors()) {
			// 新規登録画面の設定
			form.setIsNew(true);
			return "post/form";
		}

		// 画像ファイルの処理
		try {
			if (form.getFile() != null && !form.getFile().isEmpty()) {
				String imagePath = "/uploads/" + imageService.handleImageUpload(form.getFile());
				form.setImagePath(imagePath);
			}
		} catch (IOException e) {
			// エラーハンドリング
			bindingResult.reject("fileUploadError", "画像のアップロードに失敗しました。");
			form.setIsNew(true);
			return "post/form";
		}

		// 投稿のユーザーIDにログインユーザーのIDを格納
		Long currentUserId = userHelper.getCurrentUserId();
		form.setUserId(currentUserId);

		// エンティティへの変換(Postオブジェクトの作成)
		Post post = PostHelper.convertPost(form);
		// 登録実行(データベースへ保存)
		postService.insertPost(post);

		// 画像の保存処理が終わるまで待機
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "新しい投稿が作成されました");
		// RPGパターン
		return "redirect:/posts";
	}

	/**
	 * 指定されたIDの投稿の編集画面を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String ビュー名
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 現在ログインしているユーザーのIDを取得
		Long currentUserId = userHelper.getCurrentUserId();

		// 投稿IDに対応する投稿を取得
		Post target = postService.findByIdPost(id);

		if (target != null) {
			// 投稿が存在する場合、ユーザーが投稿の所有者であるか確認
			if (target.getUserId().equals(currentUserId)) {
				// 投稿のユーザーが現在ログインしているユーザーであれば、Formに変換、編集画面に遷移
				PostForm form = PostHelper.convertPostForm(target);
				// モデルに格納
				model.addAttribute("postForm", form);
				return "post/form";
			} else {
				// 所有者が異なる場合、エラーメッセージを表示してリダイレクト
				attributes.addFlashAttribute("errorMessage", "編集権限がありません");
				return "redirect:/posts";
			}

		} else {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 一覧画面へリダイレクト
			return "redirect:/posts";

		}
	}

	/**
	 * 投稿の情報を更新
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @return String ビュー名
	 */
	@PostMapping("/update")
	public String update(@Validated PostForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes) {
		// バリデーションチェック
		// 入力チェックNG:入力画面を表示する
		if (bindingResult.hasErrors()) {
			// 更新画面の設定
			form.setIsNew(false);
			return "post/form";
		}

		// 現在のログインユーザーIDを取得
		Long currentUserId = userHelper.getCurrentUserId();

		// 元の投稿の取得
		Post existingPost = postService.findByIdPost(form.getId());

		// 投稿が存在しない、またはユーザーIDが一致しない場合
		if (existingPost == null || !existingPost.getUserId().equals(currentUserId)) {
			attributes.addFlashAttribute("errorMessage", "投稿が見つからないか、編集権限がありません");
			return "redirect:/posts";
		}

		// 編集するイメージファイル名とイメージパス
		String newImageFilename = null;
		String newImagePath = null;

		try {
			// 新しい画像ファイルの処理
			if (form.getFile() != null && !form.getFile().isEmpty()) {
				try {
					// 新しい画像のファイル名を取得
					newImageFilename = imageService.handleImageUpload(form.getFile());
					// ファイルパスを相対パスでセットする
					newImagePath = "/uploads/" + newImageFilename;
					form.setImagePath(newImagePath);

				} catch (IOException e) {
					// 画像のアップロードに失敗した場合の処理
					bindingResult.reject("fileUploadError", "画像のアップロードに失敗しました。");
					form.setIsNew(false);
					return "post/form";
				}
			} else {
				// 画像ファイルが選択されていない場合は既存の画像パスをそのまま使用
				if (existingPost != null && existingPost.getImagePath() != null) {
					form.setImagePath(existingPost.getImagePath());
				}
			}

			// エンティティへの変換
			Post post = PostHelper.convertPost(form);
			// 更新処理
			postService.updatePost(post);

			// 古い画像ファイルの削除（更新処理が成功した場合のみ）
			if (existingPost != null && existingPost.getImagePath() != null) {
				// 相対パスからファイル名を取得
				String oldImageFilename = existingPost.getImagePath().replace("/uploads/", "");
				if (newImageFilename != null && !oldImageFilename.equals(newImageFilename)) {
					imageService.deleteImage(oldImageFilename);
				}
			}

			// 画像の保存処理が終わるまで待機
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			}

			// フラッシュメッセージ
			attributes.addFlashAttribute("message", "投稿が更新されました");
			// RPGパターン
			return "redirect:/posts";

		} catch (Exception e) {
			// 投稿の更新処理でエラーが発生した場合の処理
			if (newImageFilename != null) {
				// 新しい画像ファイルを削除
				imageService.deleteImage(newImageFilename);
			}

			bindingResult.reject("updateError", "投稿の更新に失敗しました。");
			form.setIsNew(false);
			return "post/form";
		}
	}

	/**
	 * 指定されたIDの投稿の削除
	 * @param id
	 * @param attributes
	 * @return String ビュー名
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
		try {
			// 現在のログインユーザーIDを取得
			Long currentUserId = userHelper.getCurrentUserId();

			// 投稿の取得
			Post post = postService.findByIdPost(id);

			if (post != null) {
				// 投稿が存在する場合、ユーザーが投稿の所有者であるか確認
				if (post.getUserId().equals(currentUserId)) {
					// 投稿に関連する画像のパスを取得
					String imagePath = post.getImagePath();

					// 投稿を削除
					postService.deletePost(id);

					// 画像ファイルを削除
					if (imagePath != null && !imagePath.isEmpty()) {
						String filename = imagePath.replace("/uploads/", "");
						imageService.deleteImage(filename);
					}

					// 投稿の削除成功の場合
					attributes.addFlashAttribute("message", "投稿が削除されました");
				} else {
					// 所有者が異なる場合の処理
					attributes.addFlashAttribute("errorMessage", "削除権限がありません");
				}
			} else {
				// 投稿が見つからない場合
				attributes.addFlashAttribute("errorMessage", "対象データがありません");
			}
		} catch (Exception e) {
			// 例外が発生した場合
			attributes.addFlashAttribute("errorMessage", "投稿の削除に失敗しました");
		}

		// RPGパターン
		return "redirect:/posts";
	}
}
