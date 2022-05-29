package edu.ifsp.es4a4.venus.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifsp.es4a4.venus.comment.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
}
