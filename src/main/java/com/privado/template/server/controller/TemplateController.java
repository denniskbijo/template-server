package com.privado.template.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.privado.template.server.bean.Template;
import com.privado.template.server.service.TemplateService;

@RestController
@RequestMapping("template")
public class TemplateController {
	@Autowired
	TemplateService templateService;

	@GetMapping("/{id}")
	public Template getTemplateById(@PathVariable String id) {
		return templateService.getTemplateById(id);
	}
}
