package edu.ifsp.es4a4.venus.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifsp.es4a4.venus.comment.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query(value = "SELECT * FROM subject WHERE name = ?1", nativeQuery = true)
    Subject findByName(String name);
}
