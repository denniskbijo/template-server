package com.privado.template.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.privado.template.server.bean.Template;
import com.privado.template.server.controller.TemplateController;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class TemplateServerApplicationTests {

	private MockMvc mvc;

	@Autowired
	private TemplateController templateController;

	@Autowired
	private WebApplicationContext context;

	@BeforeAll
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void contextLoads() {
		assertTrue(templateController != null);
	}

	@Test
	void testGetAllTemplates() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/te/");
		MvcResult result = mvc.perform(request).andReturn();
		ObjectMapper mapper = new ObjectMapper();
		Template[] templates = mapper.readValue(result.getResponse().getContentAsString(), Template[].class);
		assertEquals(3, templates.length);
	}

}
