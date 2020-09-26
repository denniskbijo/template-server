package com.privado.template.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.privado.template.server.bean.Field;
import com.privado.template.server.bean.Template;
import com.privado.template.server.exception.DocumentNotFoundException;
import com.privado.template.server.repo.TemplateRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Business logic related to {@link Template}
 * 
 * @author denniskbijo
 *
 */
@Component
@Slf4j
public class TemplateService {

	@Autowired
	private TemplateRepository repo;

	public Template getTemplateById(String id) {
		Optional<Template> optional = repo.findById(id);
		return optional.get();
	}

	public List<Template> getTemplates() {

		return repo.findAll();
	}

	/**
	 * Gets the Template for the given customerId
	 * 
	 * @param customerId
	 * @return
	 * @throws DocumentNotFoundException
	 */
	public Template getTemplateByCustomerId(String customerId) throws DocumentNotFoundException {
		validateCustomerId(customerId);
		log.debug("customerId: {}", customerId);
		List<Template> resultTemplates = repo.findByCustomerId(customerId);
		if (!resultTemplates.isEmpty())
			return resultTemplates.get(0);
		else
			throw new DocumentNotFoundException("No Template found for customer: " + customerId);
	}

	private void validateCustomerId(String customerId) {
		// Validates if the customerId is numeric
		Pattern pattern =Pattern.compile("-?\\d+");

		if (!pattern.matcher(customerId).matches()) {
			throw new NumberFormatException("CustomerId: " + customerId + " is not a number. Expected numeric value.");
		}

	}

	/**
	 * Fetches all the system templates from the database and combines the fields to
	 * generate the final list of fields. It takes union of all the system
	 * templates.
	 * 
	 * @param customerId
	 * @return
	 */
	public Template prepareTemplateForCustomerId(String customerId) {
		// Find all system templates
		List<Template> systemTemplates = repo.findByCustomerId("system");

		// Prepare template for the customer with all the system template fields
		Template customerTemplate = new Template();
		List<Field> customerFields = new ArrayList<>();
		for (Template template : systemTemplates) {
			customerFields.addAll(template.getFields());
		}
		customerTemplate.setFields(customerFields);
		customerTemplate.setCustomerId(customerId);
		customerTemplate.setType("customer");
		customerTemplate.setEntity("entity");
		customerTemplate.setLaw("base");

		// Save the base template for the customer
		return repo.save(customerTemplate);
	}


}
