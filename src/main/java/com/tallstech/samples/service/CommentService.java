package com.tallstech.samples.service;

import java.util.Optional;

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

    public Iterable<Comment> viewCommentsOfTopic(String topicId){
        return commentRepository.findCommentByTopicId(topicId);
    }


}
