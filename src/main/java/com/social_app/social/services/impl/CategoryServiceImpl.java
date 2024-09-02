package com.social_app.social.services.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.social_app.social.models.CategoryModel;
import com.social_app.social.payloads.CategoryDto;
import com.social_app.social.repository.CategoryRepo;
import com.social_app.social.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
   

    @Override
    public CategoryDto createCategory(CategoryDto category) {
     CategoryModel cat = modelMapper.map(category, CategoryModel.class);
        CategoryModel savedCat = categoryRepo.save(cat);
        return modelMapper.map(savedCat, CategoryDto.class);
       
    }

    @Override
    public CategoryDto updateCategory(CategoryDto category, Long id) {
        CategoryModel cat = categoryRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not found!")
        );
        cat.setTitle(category.getTitle());
        cat.setDescription(category.getDescription());
        CategoryModel updatedCat = categoryRepo.save(cat);
        return modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        CategoryModel cat = categoryRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not found!")
        );
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryModel> categories = categoryRepo.findAll();
        return categories.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).toList();
     }

    @Override
    public void deleteCategory(Long id) {
        CategoryModel cat = categoryRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not found!")
        );
        categoryRepo.delete(cat);
    }



   

}
