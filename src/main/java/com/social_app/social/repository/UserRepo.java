package com.social_app.social.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.social_app.social.models.UserModel;



public interface UserRepo extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);
   

    
} 
