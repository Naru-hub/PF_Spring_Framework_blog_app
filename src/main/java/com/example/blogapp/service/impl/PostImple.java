package com.example.blogapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blogapp.entity.Post;
import com.example.blogapp.repository.PostMapper;
import com.example.blogapp.service.PostService;

import lombok.RequiredArgsConstructor;

/**
 * Postサービス実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PostImple implements PostService {

	/** DI */
	private final PostMapper postMapper;

	@Override
	public List<Post> findAllPost() {
		return postMapper.selectAll();
	}

	@Override
	public Post findByIdPost(Integer id) {
		return postMapper.selectById(id);
	}

	@Override
	public void insertPost(Post post) {
		postMapper.insert(post);
	}

	@Override
	public void updatePost(Post post) {
		postMapper.update(post);
	}

	@Override
	public void deletePost(Integer id) {
		postMapper.delete(id);
	}

}
