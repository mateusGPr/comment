package edu.ifsp.es4a4.venus.comment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.ifsp.es4a4.venus.comment.model.Comment;
import edu.ifsp.es4a4.venus.comment.model.Subject;
import edu.ifsp.es4a4.venus.comment.repository.CommentRepository;
import edu.ifsp.es4a4.venus.comment.repository.SubjectRepository;

@Controller
public class CommentController {
	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping(value = "/{subject:[^\\.]*}") // (value = {"/{subject:^(?!.)}"})
	public String greeting(@PathVariable String subject, Model model) {
		model.addAttribute("subject", subject);
		Subject subjectObj = subjectRepository.findByName(subject);

		if (subjectObj == null) {
			Subject newSubject = new Subject();

			newSubject.setName(subject);
			subjectRepository.save(newSubject);

			// Isto é correto? Ou é só atribuir subjectObj = newSubject
			subjectObj = subjectRepository.findByName(subject);
		}

		model.addAttribute("comments", commentRepository.findBySubject(subjectObj.getId()));
		return "comentesobre";
	}

	@PostMapping(value = "/api")
	public String api(PostForm form) {
		String subject = form.getSubject();
		try {
			if (subject == null)
				throw new Exception("subject nulo.");

			Subject subjectObj = subjectRepository.findByName(form.getSubject());

			if (subjectObj == null) {
				subjectObj = new Subject();

				subjectObj.setName(form.getSubject());
				System.err.println("Criado");
			}

			subjectRepository.save(subjectObj);

			if (!(form.getEmail().equals("new") && form.getText().equals("new"))) {
				Comment newComment = new Comment();

				subjectObj = subjectRepository.findByName(form.getSubject());
				if(subjectObj == null) {
					System.err.println("$$$$$$$$$$$$$$$$$$$$$$$$Erro");
				}
				newComment.setEmail(form.getEmail());
				newComment.setText(form.getText());
				newComment.setSubject(subjectObj);
				commentRepository.save(newComment);
			}

		} catch (Exception e) {
			System.err.println("Exception: " + e.getLocalizedMessage());
			subject = "";
		}

		/*
		 * System.err.println(form.getSubject());
		 * System.err.println(form.getEmail());
		 * System.err.println(form.getText());
		 */

		return "redirect:/" + subject;
	}

}
