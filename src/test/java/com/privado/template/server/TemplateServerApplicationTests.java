package com.privado.template.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
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
import com.privado.template.server.exception.DocumentNotFoundException;
import com.privado.template.server.exception.InvalidCustomerIdException;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	@Order(1)
	void contextLoads() {
		assertTrue(templateController != null);
	}

	@Test
	@Order(2)
	void testGetAllTemplates() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/te/");
		MvcResult result = mvc.perform(request).andReturn();
		ObjectMapper mapper = new ObjectMapper();
		Template[] templates = mapper.readValue(result.getResponse().getContentAsString(), Template[].class);
		assertEquals(3, templates.length);
	}

	@Test
	@Order(3)
	void testPrepareTemplateForCustomerThrowsDocumentNotFoundException() throws Exception {
		String customerId = "123";
		RequestBuilder request = MockMvcRequestBuilders.get("/te/customer/" + customerId + "/templates");
		mvc.perform(request)
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof DocumentNotFoundException))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(4)
	void testPrepareTemplateThrowsDocumentNotFoundExceptionWithMessage() throws Exception {
		String customerId = "123";
		RequestBuilder request = MockMvcRequestBuilders.get("/te/customer/" + customerId + "/templates");
		mvc.perform(request)
				.andExpect(result -> assertTrue(result.getResolvedException().getMessage()
						.equals("No Template found for customer: " + customerId)));
	}

	@Test
	@Order(5)
	void testPrepareTemplateForCustomer() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/te/customer/123/templates");
		MvcResult result = mvc.perform(request).andReturn();
		ObjectMapper mapper = new ObjectMapper();
		Template template = mapper.readValue(result.getResponse().getContentAsString(), Template.class);
		assertEquals("123", template.getCustomerId());
	}

	@Test
	@Order(6)
	void testPrepareTemplateForCustomerThrowsInvalidCustomerIdException() throws Exception {
		String customerId = "abc";
		RequestBuilder request = MockMvcRequestBuilders.post("/te/customer/" + customerId + "/templates");
		mvc.perform(request)
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCustomerIdException))
				.andExpect(result -> assertEquals(
						"CustomerId: " + customerId + " is not a number. Expected numeric value.",
						result.getResolvedException().getMessage()));
	}

}
