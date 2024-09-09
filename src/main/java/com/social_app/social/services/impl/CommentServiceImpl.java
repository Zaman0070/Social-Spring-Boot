package com.social_app.social.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.social_app.social.models.CommentModel;
import com.social_app.social.models.PostModel;
import com.social_app.social.payloads.CommentDto;
import com.social_app.social.repository.CommentRepo;
import com.social_app.social.repository.PostRepo;
import com.social_app.social.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto comment, Long postId) {
        PostModel post = postRepo.findById(postId).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post is not found!"));
        CommentModel newComment = modelMapper.map(comment, CommentModel.class);
        newComment.setPost(post);
        CommentModel savedComment = commentRepo.save(newComment);
        return modelMapper.map(savedComment, CommentDto.class);
    }



    @Override
    public List<CommentDto> getAllComment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllComment'");
    }

    @Override
    public void deleteComment(Long id) {
        CommentModel comment = commentRepo.findById(id).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Comment is not found!"));
        commentRepo.delete(comment);
    }

}
