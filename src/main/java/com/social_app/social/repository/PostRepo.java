package com.social_app.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social_app.social.models.CategoryModel;
import com.social_app.social.models.PostModel;
import com.social_app.social.models.UserModel;

public interface PostRepo extends JpaRepository<PostModel, Long> {


    List<PostModel> findByUser(UserModel user);
    List<PostModel> findByCategory(CategoryModel category);
    List<PostModel> findByTitleContaining(String title);
    
}
