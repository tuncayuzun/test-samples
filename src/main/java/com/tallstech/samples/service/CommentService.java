package com.tallstech.samples.service;

import java.util.List;

import com.tallstech.samples.model.Comment;
import com.tallstech.samples.repository.CommentRepository;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public Comment addCommentToTopic(Comment comment){
     return commentRepository.save(comment);
    }

    public List<Comment> viewCommentsOfTopic(Long topicId){
        return commentRepository.findCommentsByTopicId(topicId);
    }


}
