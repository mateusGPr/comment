package edu.ifsp.es4a4.venus.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifsp.es4a4.venus.comment.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    public static final String SELECT_COMMENT_WHERE_SUBJECT_ID = "SELECT comment FROM Comment comment WHERE comment.subject.id =:subjectId ORDER BY comment.created DESC";

    @Query(value = SELECT_COMMENT_WHERE_SUBJECT_ID)
    List<Comment> findBySubject(Long subjectId);

    List<Comment> findTop10ByOrderByCreatedDesc();
}
