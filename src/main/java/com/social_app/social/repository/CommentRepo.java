package com.social_app.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.social_app.social.models.CommentModel;

public interface CommentRepo extends JpaRepository<CommentModel, Long> {

}
