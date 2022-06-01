package edu.ifsp.es4a4.venus.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifsp.es4a4.venus.comment.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    public static final String SELECT_SUBJECT_WHERE_NAME = "SELECT subject FROM Subject subject WHERE subject.name =:name";

    @Query(value = SELECT_SUBJECT_WHERE_NAME)
    Subject findByName(String name);
}
