package com.tallstech.samples.repository;


import java.util.List;

import com.tallstech.samples.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentsByTopicId(Long topicId);
}
