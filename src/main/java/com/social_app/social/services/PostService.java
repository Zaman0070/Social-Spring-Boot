package com.social_app.social.services;

import java.util.List;

import com.social_app.social.payloads.PostDto;
import com.social_app.social.payloads.PostResponse;

public interface PostService {

    PostDto createPost(PostDto post,Long categoryId, Long userId);
    PostDto updatePost(PostDto post, Long id);
    PostDto getPost(Long id);
    PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy);
    void deletePost(Long id);
    List<PostDto> getPostByUser(Long id);
    List<PostDto> getPostByCategory(Long id);
    List<PostDto> searchPost(String keyword);
    
}
