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

import edu.ifsp.es4a4.venus.comment.model.Subject;
import edu.ifsp.es4a4.venus.comment.repository.SubjectRepository;

@RestController
@RequestMapping("/api.rest")
public class SubjectControler {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects(@RequestParam(required = false) String name) {
        List<Subject> subjects = new ArrayList<Subject>();

        if (name == null)
            subjectRepository.findAll().forEach(subjects::add);
        else
            subjects.add(subjectRepository.findByName(name));

        if (subjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found subject with id = " + id));

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/subjects")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject _subject = subjectRepository
                .save(new Subject(subject.getName()));

        return new ResponseEntity<>(_subject, HttpStatus.CREATED);
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") long id, @RequestBody Subject subject) {
        Subject _subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found subject with id = " + id));
        _subject.setName(subject.getName());

        return new ResponseEntity<>(subjectRepository.save(_subject), HttpStatus.OK);
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable("id") long id) {
        subjectRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Somente em desenvolvimento
     * TODO: Deletar ou comentar
     */
    @DeleteMapping("/subjects")
    public ResponseEntity<HttpStatus> deleteAllSubjects() {
        subjectRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}