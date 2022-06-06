package edu.ifsp.es4a4.venus.comment.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.es4a4.venus.comment.model.Comment;
import edu.ifsp.es4a4.venus.comment.model.Subject;
import edu.ifsp.es4a4.venus.comment.repository.CommentRepository;
import edu.ifsp.es4a4.venus.comment.repository.SubjectRepository;

@RestController
@RequestMapping("/api.rest")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam(required = false) Long id) {
        List<Comment> comments;

        if (id == null) {
            comments = new ArrayList<Comment>();
            commentRepository.findAll().forEach(comments::add);
        } else
            comments = commentRepository.findBySubject(id);

        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found comment with id = " + id));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/comments/{name}")
    public ResponseEntity<Comment> createComment(@PathVariable("name") String _name, @RequestBody Comment comment) {
        String name = _name.toLowerCase();
        Subject subject = subjectRepository.findByName(name);

        if (subject == null) {
            subject = subjectRepository.save(new Subject(name));

            if (subject == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        Comment _comment = commentRepository
                .save(new Comment(comment.getEmail(), comment.getText(), subject));
        return new ResponseEntity<>(_comment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment comment) {
        Comment _comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found comment with id = " + id));
        _comment.setEmail(comment.getEmail());

        return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        commentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Somente em desenvolvimento
     * TODO: Deletar ou comentar
     */
    @DeleteMapping("/comments")
    public ResponseEntity<HttpStatus> deleteAllComments() {
        commentRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}