package edu.ifsp.es4a4.venus.comment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity {
    @Column(name = "subject", length = 255)
    @NotEmpty
    private String name;
    
    public Subject() {}

    public Subject(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name.toLowerCase();
    }
}
