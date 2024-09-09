package com.social_app.social.services;

import com.social_app.social.payloads.CommentDto;
import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto comment, Long postId);
    List<CommentDto> getAllComment();
    void deleteComment(Long id);

}
