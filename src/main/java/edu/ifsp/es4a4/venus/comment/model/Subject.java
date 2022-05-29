package edu.ifsp.es4a4.venus.comment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subject")
public class Subject {
    @Id @GeneratedValue
    private Long subjectId;

    @Column(length = 255)
    @Size(min = 0, max = 255)
    @NotNull @NotBlank
    private String name;

    @OneToMany
    private List<Comment> comments;

    public Long getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public void addComments(Comment comment) {
        this.comments.add(comment);
    }
    public Long getId() {
        return subjectId;
    }
    public void setId(Long id) {
        this.subjectId = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
