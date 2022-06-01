package edu.ifsp.es4a4.venus.comment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity{
    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_generator")
    // private Long id;

    // public Long getId() {
    //     return id;
    // }

    // public void setId(final Long id) {
    //     this.id = id;
    // }

    // public boolean isNew() {
    //     return this.id == null;
    // }
    @Column(name = "subject", length = 255)
    @NotEmpty
    private String name;
    /*
     * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     * 
     * @JoinColumn(name = "subject_id")
     * private Set<Comment> comments = new LinkedHashSet<>();
     * 
     * public Collection<Comment> getComments() {
     * return comments;
     * }
     * 
     * public void addComments(Comment comment) {
     * this.comments.add(comment);
     * }
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
