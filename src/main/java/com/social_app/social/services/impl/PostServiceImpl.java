package com.social_app.social.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.social_app.social.repository.UserRepo;
import com.social_app.social.repository.CategoryRepo;
import com.social_app.social.models.CategoryModel;
import com.social_app.social.models.PostModel;
import com.social_app.social.models.UserModel;
import com.social_app.social.payloads.PostDto;
import com.social_app.social.payloads.PostResponse;
import com.social_app.social.repository.PostRepo;
import com.social_app.social.services.PostService;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto post, Long categoryId, Long userId) {
        System.out.println("PostServiceImpl.createPost()${categoryId} = " + categoryId);
        UserModel user = userRepo.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found!"));
        CategoryModel category = categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not found!"));
        PostModel newPost = modelMapper.map(post, PostModel.class);
        newPost.setImageUrl("default.png");
        newPost.setCreatedAt(new Date());
        newPost.setCategory(category);
        newPost.setUser(user);

        PostModel savedPost = postRepo.save(newPost);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto post, Long id) {
        PostModel postModel = postRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post is not found!"));
        postModel.setTitle(post.getTitle());
        postModel.setContent(post.getContent());
        postModel.setUpdatedAt(new Date());
        postModel.setImageUrl(post.getImageUrl());
        PostModel updatedPost = postRepo.save(postModel);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto getPost(Long id) {
        PostModel post = postRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post is not found!"));
        return modelMapper.map(post, PostDto.class);

    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy) {
        // Sort sort = null;
        // if (sortBy.equals("asc")) {
        //     sort = Sort.by(sortBy).ascending();
        // } else {
        //     sort = Sort.by(sortBy).descending();
        // }
        // Pageable page = PageRequest.of(pageNo, pageSize,sort);
        Pageable page = PageRequest.of(pageNo, pageSize,Sort.by(sortBy).descending());
        Page<PostModel> pagePosts = postRepo.findAll(page);
        List<PostModel> posts = pagePosts.getContent();
        List<PostDto> postList= posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();

        PostResponse response = new PostResponse();
        response.setPosts(postList);
        response.setPageNo(pagePosts.getNumber());
        response.setPageSize(pagePosts.getSize());
        response.setTotalElements((int) pagePosts.getTotalElements());
        response.setTotalPages(pagePosts.getTotalPages());
        response.setLast(pagePosts.isLast());
        return response;
    }

    @Override
    public void deletePost(Long id) {
        PostModel post = postRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post is not found!"));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByUser(Long id) {
        UserModel user = userRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found!"));
        List<PostModel> posts = postRepo.findByUser(user);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostByCategory(Long id) {
        CategoryModel category = categoryRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not found!"));
        List<PostModel> posts = postRepo.findByCategory(category);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
       List<PostModel> posts = postRepo.findByTitleContaining(keyword);
         return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

}
