package edu.ifsp.es4a4.venus.comment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.ifsp.es4a4.venus.comment.model.Comment;
import edu.ifsp.es4a4.venus.comment.model.PostForm;
import edu.ifsp.es4a4.venus.comment.model.Subject;
import edu.ifsp.es4a4.venus.comment.repository.CommentRepository;
import edu.ifsp.es4a4.venus.comment.repository.SubjectRepository;

@Controller
public class CommentAboutController {
	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/")
	public String home(Model model) {
		List<Comment> comments = commentRepository.findTop10ByOrderByCreatedDesc();

		model.addAttribute("comments", comments);
		return "home";
	}

	@GetMapping(value = "/{_subject}") // (value = {"/{subject:^(?!.)}"})
	public String getSubject(@PathVariable String _subject, Model model) {
		String subject = _subject.toLowerCase();
		model.addAttribute("subject", subject);
		Subject subjectObj = subjectRepository.findByName(subject);

		if (subjectObj == null) {
			subjectObj = subjectRepository.save(new Subject(subject));
		}

		model.addAttribute("comments", commentRepository.findBySubject(subjectObj.getId()));
		return "comentesobre";
	}

	@PostMapping(value = "/api.post")
	public String api(PostForm form) {
		String subject = form.getSubject().toLowerCase();
		try {
			if (subject == null)
				throw new Exception("commentAboutController subject nulo.");

			Subject subjectObj = subjectRepository.findByName(subject);

			if (subjectObj == null) {
				subjectObj = subjectRepository.save(new Subject(subject));
			}

			if ((form.getEmail().length() > 0) && (form.getText().length() > 0)) {
				Comment newComment = new Comment(form.getEmail(), form.getText(), subjectObj);

				commentRepository.save(newComment);
			}

		} catch (Exception e) {
			System.err.println("Exception: " + e.getLocalizedMessage());
			subject = "";
		}
		return "redirect:/" + subject;
	}

}
