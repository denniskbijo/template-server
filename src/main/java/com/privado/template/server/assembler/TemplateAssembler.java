package com.privado.template.server.assembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.privado.template.server.bean.Template;
import com.privado.template.server.controller.TemplateController;

/**
 * Creates hypermedia links for template for Spring HATEOS
 * 
 * @author denniskbijo
 *
 */
@Component
public class TemplateAssembler implements RepresentationModelAssembler<Template, EntityModel<Template>> {

	@Override
	public EntityModel<Template> toModel(Template template) {

		return EntityModel.of(template, //
				linkTo(methodOn(TemplateController.class).getTemplateById(template.getId())).withSelfRel(),
				linkTo(methodOn(TemplateController.class).getTemplates()).withRel("templates"));
	}
	
}