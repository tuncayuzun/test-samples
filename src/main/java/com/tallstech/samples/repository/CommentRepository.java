package com.tallstech.samples.repository;


import java.util.Optional;

import com.tallstech.samples.model.Comment;
import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findCommentByTopicId(String topicId);
}
