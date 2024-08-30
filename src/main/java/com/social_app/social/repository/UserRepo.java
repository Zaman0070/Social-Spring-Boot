package com.social_app.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social_app.social.models.UserModel;



public interface UserRepo extends JpaRepository<UserModel, Long> {
   

    
} 
