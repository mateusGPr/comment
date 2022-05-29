package edu.ifsp.es4a4.venus.comment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import javax.persistence.GeneratedValue;

@Entity
@Table(name = "comments")
public class Comment {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 255)
    @Size(min = 0, max = 255)
    @NotNull @NotBlank
    private String text;

    @Email @NotNull @NotBlank
    private String email;

    @ManyToOne
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
