package com.tallstech.samples.controller;

import com.tallstech.samples.model.Comment;
import com.tallstech.samples.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@Valid @RequestBody Comment comment){
       return commentService.addCommentToTopic(comment);
    }

    @GetMapping("{topicId}")
    public Iterable<Comment> getCommentsByTopicId(@PathVariable String topicId){
        return commentService.viewCommentsOfTopic(topicId);
    }
}
