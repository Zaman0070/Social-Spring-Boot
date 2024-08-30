package com.social_app.social.services;

import java.util.List;

import com.social_app.social.payloads.CategoryDto;


public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto category, Long id);
    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategory();
    void deleteCategory(Long id);

    
}
