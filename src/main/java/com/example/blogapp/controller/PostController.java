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

	/**
	 * 投稿一覧を表示
	 */
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("posts", postService.findAllPost());
		return "post/list";
	}

	/**
	 * 指定されたIDの投稿の詳細を表示
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 投稿IDに対応する投稿を取得
		Post post = postService.findByIdPost(id);

		// 対象データがあるか
		if (post != null) {
			// データがある場合はモデルに格納
			model.addAttribute("post", postService.findByIdPost(id));
			return "post/detail";
		} else {
			// 対象データがない場合
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			return "redirect:/posts";
		}
	}

	/**
	 *  新規登録画面を表示
	 */
	@GetMapping("/form")
	public String newPost(@ModelAttribute PostForm form) {
		// 新規登録画面の設定
		form.setIsNew(true);
		return "post/form";
	}

	/**
	 * 新規登録を実行
	 */
	@PostMapping("/save")
	public String create(@Validated PostForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes) {
		// === バリデーションチェック ===
		// 入力チェックNG:入力画面を表示する
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.hasErrors());
			// 新規登録画面の設定
			form.setIsNew(true);
			return "post/form";
		}

		// 画像ファイルの処理
        try {
        	 if (form.getFile() != null && !form.getFile().isEmpty()) {
                 String imagePath = imageService.handleImageUpload(form.getFile());
                 form.setImagePath(imagePath); 
             }
        } catch (IOException e) {
            // エラーハンドリング
            bindingResult.reject("fileUploadError", "画像のアップロードに失敗しました。");
            form.setIsNew(true);
            return "post/form";
        }

		// エンティティへの変換(Postオブジェクトの作成)
		Post post = PostHelper.convertPost(form);
		// 登録実行(データベースへ保存)
		postService.insertPost(post);
		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "新しい投稿が作成されました");
		// RPGパターン
		return "redirect:/posts";
	}

	/**
	 * 指定されたIDの投稿の編集画面を表示
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// IDに対応する「すること」を取得
		Post target = postService.findByIdPost(id);

		if (target != null) {
			// 対象データがある場合はFormへ変換
			PostForm form = PostHelper.convertPostForm(target);
			// モデルに格納
			model.addAttribute("postForm", form);
			return "post/form";
		} else {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 一覧画面へリダイレクト
			return "redirect:/posts";

		}
	}

	/**
	 * 投稿の情報を更新
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

		// エンティティへの変換
		Post post = PostHelper.convertPost(form);
		// 更新処理
		postService.updatePost(post);
		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "投稿が更新されました");
		// RPGパターン
		return "redirect:/posts";
	}

	/**
	 * 指定されたIDの投稿の削除
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
		// 削除処理
		postService.deletePost(id);
		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "投稿が削除されました");
		// RPGパターン
		return "redirect:/posts";
	}
}
