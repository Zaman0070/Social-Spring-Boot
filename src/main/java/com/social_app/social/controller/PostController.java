package com.social_app.social.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.social_app.social.configs.AppConstant;
import com.social_app.social.payloads.PostDto;
import com.social_app.social.payloads.PostResponse;
import com.social_app.social.services.FileService;
import com.social_app.social.services.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/users/{userId}/categories/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post, @PathVariable Long categoryId,
            @PathVariable Long userId) {
        PostDto createPost = postService.createPost(post, categoryId, userId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Long id) {
        List<PostDto> posts = postService.getPostByUser(id);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Long id) {
        List<PostDto> posts = postService.getPostByCategory(id);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy) {
        PostResponse posts = postService.getAllPost(
                pageNo, pageSize, sortBy);
        return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        PostDto post = postService.getPost(id);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<String>("Post deleted successfully", HttpStatus.OK);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto post) {
        PostDto updatedPost = postService.updatePost(post, id);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword) {
        List<PostDto> posts = postService.searchPost(keyword);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    /////////////////////////// File Upload ///////////////////////////

    @PostMapping("/post/image/upload/{id}")
    public ResponseEntity<PostDto> uploadFile(@RequestParam("image") MultipartFile image, @PathVariable Long id)
            throws IOException {
        PostDto post = postService.getPost(id);
        String fileName = fileService.uploadFile(path, image);
        post.setImageUrl(fileName);
        PostDto updatedPostDto = postService.updatePost(post, id);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    

}
