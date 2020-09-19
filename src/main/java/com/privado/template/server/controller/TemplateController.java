package com.privado.template.server.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.privado.template.server.assembler.TemplateAssembler;
import com.privado.template.server.bean.Template;
import com.privado.template.server.service.TemplateService;

/**
 * REST Controller for APIs related to {@link Template}
 * 
 * @author denniskbijo
 *
 */
@RestController
@RequestMapping("templates")
public class TemplateController {

	@Autowired
	TemplateService templateService;

	@Autowired
	TemplateAssembler templateAssembler;

	@GetMapping("")
	public CollectionModel<EntityModel<Template>> getTemplates() {
		List<EntityModel<Template>> templates = templateService.getTemplates().stream().map(templateAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(templates, linkTo(methodOn(TemplateController.class).getTemplates()).withSelfRel());
	}
	@GetMapping("/{id}")
	public EntityModel<Template> getTemplateById(@PathVariable String id) {
		Template template = templateService.getTemplateById(id);
		return templateAssembler.toModel(template);

	}
}
