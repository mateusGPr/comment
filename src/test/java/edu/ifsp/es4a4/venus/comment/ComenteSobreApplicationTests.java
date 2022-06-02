package edu.ifsp.es4a4.venus.comment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ifsp.es4a4.venus.comment.model.Comment;
import edu.ifsp.es4a4.venus.comment.model.Subject;

@SpringBootTest
@AutoConfigureMockMvc
class ComenteSobreApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testPostComment() throws Exception {
		Comment comment = new Comment("test@test.com", "Test text...", new Subject("test"));

		mockMvc.perform(post("/api.rest/comments/test")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(comment)))
				.andExpect(status().isCreated());
	}

	@Test
	void testGetComments() throws Exception {
		mockMvc.perform(get("/api.rest/comments"))
				.andDo(print())
				.andExpect(content().contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetSubjects() throws Exception {
		mockMvc.perform(get("/api.rest/subjects"))
				.andDo(print())
				.andExpect(content().contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetSubject() throws Exception {
		this.mockMvc.perform(get("/test"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("subject", "comments"))
				.andExpect(view().name("comentesobre"));
	}
}