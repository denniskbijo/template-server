package com.privado.template.server;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.privado.template.server.controller.TemplateController;

@SpringBootTest
class TemplateServerApplicationTests {

	@Autowired
	private TemplateController templateController;

	@Test
	void contextLoads() {
		assertTrue(templateController != null);
	}

}
