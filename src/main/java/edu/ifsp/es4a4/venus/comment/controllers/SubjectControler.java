package edu.ifsp.es4a4.venus.comment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ifsp.es4a4.venus.comment.repository.CommentRepository;
import edu.ifsp.es4a4.venus.comment.repository.SubjectRepository;

@Controller
public class SubjectControler {
	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private CommentRepository commentRepository;
}