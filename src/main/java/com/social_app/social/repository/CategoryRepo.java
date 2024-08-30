package com.social_app.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social_app.social.models.CategoryModel;

public interface CategoryRepo extends JpaRepository<CategoryModel, Long> {

}
