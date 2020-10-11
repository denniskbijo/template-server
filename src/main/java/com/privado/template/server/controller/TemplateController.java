package com.privado.template.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.privado.template.server.bean.Template;
import com.privado.template.server.exception.DocumentNotFoundException;
import com.privado.template.server.exception.InvalidCustomerIdException;
import com.privado.template.server.service.TemplateService;

/**
 * REST Controller for APIs related to {@link Template}
 * 
 * @author denniskbijo
 *
 */
@RestController
@RequestMapping("te")
public class TemplateController {

	@Autowired
	private TemplateService templateService;

	@GetMapping("")
	public List<Template> getTemplates() {

		return templateService.getTemplates();
	}

	@GetMapping("/{id}")
	public Template getTemplateById(@PathVariable String id) {

		return templateService.getTemplateById(id);
	}

	@GetMapping("/customer/{customerId}/templates")
	public Template getTemplateByCustomerId(@PathVariable String customerId)
			throws DocumentNotFoundException, InvalidCustomerIdException {

		return templateService.getTemplateByCustomerId(customerId);
	}

	@PostMapping("/customer/{customerId}/templates")
	public Template prepareTemplateForCustomerId(@PathVariable String customerId) throws InvalidCustomerIdException {

		return templateService.prepareTemplateForCustomerId(customerId);
	}

}
