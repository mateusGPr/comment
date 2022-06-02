package edu.ifsp.es4a4.venus.comment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;

    @Column(name = "text")
    @NotEmpty
    @Lob
    private String text;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Subject subject;

    public Comment(final String email, final String text, final Subject subject) {
        this.email = email;
        this.text = text;
        this.subject = subject;
    }

    public Comment(){}

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(final Subject subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
